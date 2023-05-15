package com.jlw.ssp.utils;

import com.jlw.ssp.entity.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jlw.ssp.constant.GlobalConstant.*;

public class RegexUtil {

    public static Score getAllScore(String response){
        Score score = new Score();
        ArrayList<String> regexList = new ArrayList<>();
        HashMap<String, Float> hashMap = new HashMap<>();
        regexList.add(ACCURACY_SCORE_REGEX_PATTERN);
        regexList.add(EMOTION_SCORE_REGEX_PATTERN);
        regexList.add(FLUENCY_SCORE_REGEX_PATTERN);
        regexList.add(TOTAL_SCORE_REGEX_PATTERN);
        for (String tempRegex : regexList){
            Pattern pattern = Pattern.compile(tempRegex);
            Matcher matcher = pattern.matcher(response);
            String regexTemp = "";
            while(matcher.find()) {
                regexTemp = matcher.group();
            }
            Pattern scorePattern = Pattern.compile(SCORE_REGEX_PATTERN);
            Matcher scoreMatcher = scorePattern.matcher(regexTemp);
            while(scoreMatcher.find()) {
                hashMap.put(tempRegex, Float.parseFloat(scoreMatcher.group()));
            }
        }
        score.setAccuracyScore(hashMap.get(ACCURACY_SCORE_REGEX_PATTERN).toString());
        score.setFluencyScore(hashMap.get(FLUENCY_SCORE_REGEX_PATTERN).toString());
        score.setEmotionScore(hashMap.get(EMOTION_SCORE_REGEX_PATTERN).toString());
        score.setTotalScore(hashMap.get(TOTAL_SCORE_REGEX_PATTERN).toString());
        return score;
    }
}
