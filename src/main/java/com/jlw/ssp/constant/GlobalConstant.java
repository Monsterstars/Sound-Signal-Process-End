package com.jlw.ssp.constant;

public class GlobalConstant {

    public static final int ATTEMPTS_START_TIME = 0;
    public static final int ATTEMPTS_LIMIT = 150;
    public static final String WEB_IATWS_HOST_URL = "https://iat-api.xfyun.cn/v2/iat";
    public static final String VOICEPRINT_RECOGNITION_HOST_URL = "https://api.xf-yun.com/v1/private/s782b4996";
    public static final String ISE_HOST_URL = "https://ise-api.xfyun.cn/v2/open-ise";
    public static final int LOOP_SLEEP_TIME = 500;
    public static final String HTTP_PREFIX = "http://";
    public static final String HTTPS_PREFIX = "https://";
    public static final String WS_PREFIX = "ws://";
    public static final String WSS_PREFIX = "wss://";
    public static final int XML_SPLIT_LINE = 4;
    public static final int MAX_ONCE_BYTE = 1024;
    public static final String ACCURACY_SCORE_REGEX_PATTERN = "accuracy_score=\"\\d*\\.\\d*\"";
    public static final String EMOTION_SCORE_REGEX_PATTERN = "emotion_score=\"\\d*\\.\\d*\"";
    public static final String FLUENCY_SCORE_REGEX_PATTERN = "fluency_score=\"\\d*\\.\\d*\"";
    public static final String TOTAL_SCORE_REGEX_PATTERN = "total_score=\"\\d*\\.\\d*\"";
    public static final String SCORE_REGEX_PATTERN = "\\d*\\.\\d*";
    public static final String UUID_REPLACE_TARGET = "-";
    public static final String UUID_REPLACEMENT = "";
    public static final String PINYIN_SEPARATE = " ";
    public static final boolean PINYIN_RETAIN = true;
    public static final String FAIL_GET_WEB_IATWS = "获取语音识别结果超时";
    public static final String FAIL_SEARCH_FEATURES = "搜索音纹超时";
    public static final String FAIL_ISE = "评测超时";
    public static final String FFMPEG_PCM_TO_MP3 = " -y -f s16be -ac 2 -ar 16000 -acodec pcm_s16le -i %s %s";
    public static final String FFMPEG_MP3_TO_PCM = " -y -i %s -acodec pcm_s16le -f s16le -ac 2 -ar 16000 %s";
    public static final String FFMPEG_WEBM_TO_MP3 = " -i %s -vn -acodec libmp3lame -q:a 2 %s";
    public static final String FFMPEG_WEBM_TO_PCM = " -i %s -vn -f s16le -ar 16000 -acodec pcm_s16le %s";
}
