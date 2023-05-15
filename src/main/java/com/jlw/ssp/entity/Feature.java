package com.jlw.ssp.entity;

public class Feature {
    private String text;
    private String pinyin;
    private String featureId;
    private String userName;
    private Score score;

    public Feature() {
    }

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

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Feature(String text, String pinyin, String featureId, String userName, Score score) {
        this.text = text;
        this.pinyin = pinyin;
        this.featureId = featureId;
        this.userName = userName;
        this.score = score;
    }
}
