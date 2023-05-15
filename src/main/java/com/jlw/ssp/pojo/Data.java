package com.jlw.ssp.pojo;

public class Data {

    String id;
    String userName;
    String accuracyScore;
    String emotionScore;
    String fluencyScore;
    String totalScore;
    String text;
    String pinyin;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Data(String id, String userName, String accuracyScore, String emotionScore, String fluencyScore, String totalScore, String text, String pinyin) {
        this.id = id;
        this.userName = userName;
        this.accuracyScore = accuracyScore;
        this.emotionScore = emotionScore;
        this.fluencyScore = fluencyScore;
        this.totalScore = totalScore;
        this.text = text;
        this.pinyin = pinyin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccuracyScore() {
        return accuracyScore;
    }

    public void setAccuracyScore(String accuracyScore) {
        this.accuracyScore = accuracyScore;
    }

    public String getEmotionScore() {
        return emotionScore;
    }

    public void setEmotionScore(String emotionScore) {
        this.emotionScore = emotionScore;
    }

    public String getFluencyScore() {
        return fluencyScore;
    }

    public void setFluencyScore(String fluencyScore) {
        this.fluencyScore = fluencyScore;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }


    public Data() {
    }
}
