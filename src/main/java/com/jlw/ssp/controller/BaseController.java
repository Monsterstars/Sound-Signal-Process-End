package com.jlw.ssp.controller;

import com.jlw.ssp.constant.UserConstant;
import com.jlw.ssp.pojo.Data;
import com.jlw.ssp.entity.Feature;
import com.jlw.ssp.entity.Response;
import com.jlw.ssp.entity.Result;
import com.jlw.ssp.service.BaseService;
import com.jlw.ssp.thirdpart.Ise;
import com.jlw.ssp.thirdpart.WebIATWS;
import com.jlw.ssp.thirdpart.CreateFeature;
import com.jlw.ssp.thirdpart.SearchFeature;
import com.jlw.ssp.utils.CommandUtils;
import com.jlw.ssp.utils.HttpResult;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.jlw.ssp.constant.GlobalConstant.*;
import static com.jlw.ssp.enums.FileType.*;

@RestController
@CrossOrigin
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private BaseService baseService;

    @Autowired
    private UserConstant userConstant;

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public HttpResult getAll(@RequestParam MultipartFile voiceFile) throws Exception {
        /**
         * Step:
         *      1、获取pcm、mp3文件的路径
         *      2、生成唯一字符串
         *      3、创建返回的结构体
         *      4、调用语音转文字 API
         *      5、调用声纹库 API
         *          5.1、查询声纹库中最接近的一个声纹的featureId
         *          5.2、把音频文件添加进声纹库
         *      6、调用语音评测 API
         *      7、返回结果
         *
         */
        int attempts = ATTEMPTS_START_TIME;

        String uuid = UUID.randomUUID().toString().replace(UUID_REPLACE_TARGET, UUID_REPLACEMENT);
        String fileName = userConstant.SOUNDS_RESOURCES_PATH + uuid + WEBM.getSuffix();
        voiceFile.transferTo(new File(fileName));
        String webmPath = fileName;
        String pcmPath = fileName.replace(WEBM.getSuffix(), PCM.getSuffix());;
        String mp3Path = fileName.replace(WEBM.getSuffix(), MP3.getSuffix());

        CommandUtils.convertFile(userConstant.FFMPEG_PATH, WEBM.getType(), MP3.getConvertFlag(), webmPath);
        CommandUtils.convertFile(userConstant.FFMPEG_PATH, WEBM.getType(), PCM.getConvertFlag(), webmPath);

        Result result = new Result();
        result.setThisFeatureId(uuid);

        WebIATWS webIATWS = new WebIATWS(userConstant.APP_ID);
        webIATWS.setFile(pcmPath);
        String WebIATWSAuthUrl = webIATWS.getAuthUrl(WEB_IATWS_HOST_URL, userConstant.API_KEY, userConstant.API_SECRET);
        OkHttpClient webIATWSClient = new OkHttpClient.Builder().build();
        String url = WebIATWSAuthUrl.replace(HTTP_PREFIX, WS_PREFIX).replace(HTTPS_PREFIX, WSS_PREFIX);
        Request WebIATWSRequest = new Request.Builder().url(url).build();
        webIATWSClient.newWebSocket(WebIATWSRequest, webIATWS);
        while ("".equals(webIATWS.getFinalText())) {
            if (attempts < ATTEMPTS_LIMIT) {
                Thread.sleep(LOOP_SLEEP_TIME);
                attempts++;
            }
            else {
                logger.error(FAIL_GET_WEB_IATWS);
                return HttpResult.fail().add(FAIL_GET_WEB_IATWS);
            }
        }
        attempts = ATTEMPTS_START_TIME;
        result.setText(webIATWS.getFinalText());
        result.setPinyin(PinyinHelper.toHanYuPinyinString(webIATWS.getFinalText(), new HanyuPinyinOutputFormat(), PINYIN_SEPARATE, PINYIN_RETAIN));

        SearchFeature searchFeature = new SearchFeature();
        searchFeature.doSearchFeature(VOICEPRINT_RECOGNITION_HOST_URL, userConstant.APP_ID, userConstant.API_SECRET, userConstant.API_KEY, mp3Path, userConstant.GROUP_ID, userConstant.MIN_SCORE);
        while (!searchFeature.isFlag()) {
            if (attempts < ATTEMPTS_LIMIT){
                Thread.sleep(LOOP_SLEEP_TIME);
                attempts++;
            }
            else {
                logger.error(FAIL_SEARCH_FEATURES);
                return HttpResult.fail().add(FAIL_SEARCH_FEATURES);
            }
        }
        attempts = ATTEMPTS_START_TIME;
        result.setMaxScoreFeatureId(searchFeature.getMaxScoreFeatureId());
        CreateFeature createFeature = new CreateFeature();
        createFeature.doCreateFeature(VOICEPRINT_RECOGNITION_HOST_URL, userConstant.APP_ID, userConstant.API_SECRET, userConstant.API_KEY, mp3Path, uuid, userConstant.GROUP_ID);

        Ise ise = new Ise(userConstant.APP_ID);
        ise.setText(webIATWS.getFinalText());
        ise.setFile(pcmPath);
        String iseAuthUrl = ise.getAuthUrl(ISE_HOST_URL, userConstant.API_KEY, userConstant.API_SECRET);
        OkHttpClient iseClient = new OkHttpClient.Builder().build();
        String iseUrl = iseAuthUrl.replace(HTTP_PREFIX, WS_PREFIX).replace(HTTPS_PREFIX, WSS_PREFIX);
        Request iseRequest = new Request.Builder().url(iseUrl).build();
        iseClient.newWebSocket(iseRequest, ise);
        while (null == ise.getResult()) {
            if (attempts < ATTEMPTS_LIMIT){
                Thread.sleep(LOOP_SLEEP_TIME);
                attempts++;
            }
            else {
                logger.error(FAIL_ISE);
                return HttpResult.fail().add(FAIL_ISE);
            }
        }
        result.setScore(ise.getResult());

        String temp = baseService.getUserNameByFeatureId(result.getMaxScoreFeatureId());
        String oldUserName = (null == temp) ? uuid : temp;
        List<Feature> featureList = baseService.getDataByUserName(oldUserName);

        Data data = new Data(uuid, oldUserName, result.getScore().getAccuracyScore(), result.getScore().getEmotionScore(), result.getScore().getFluencyScore(), result.getScore().getTotalScore(), result.getText(),result.getPinyin());
        Response response = new Response(result.getText(), result.getPinyin(), uuid, oldUserName, result.getScore(), featureList);
        logger.info(response.toString());
        if (baseService.addData(data)) {
            return HttpResult.success().add(response);
        } else {
            return HttpResult.fail();
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public HttpResult updateByFeatureId(@RequestParam String oldUserName, @RequestParam String newUserName) {
        baseService.updateData(oldUserName, newUserName);
        return HttpResult.success();
    }

    @RequestMapping(value = "/play", method = RequestMethod.GET)
    public void playByFeatureId(@RequestParam String featureId, HttpServletResponse httpServletResponse) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(userConstant.SOUNDS_RESOURCES_PATH + featureId + MP3.getSuffix());
        byte[] data = new byte[MAX_ONCE_BYTE]; // 每次最多读取1024字节
        int len = 0; // 每次读取的字节数
        while ((len = fileInputStream.read(data)) != -1) {
            httpServletResponse.getOutputStream().write(data, 0, len);
        }
    }
}
