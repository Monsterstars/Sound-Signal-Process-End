package com.jlw.ssp.entity;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private String text;
    private String pinyin;
    private String featureId;
    private String userName;
    private Score score;
    private List<Feature> featureList;

    public Response() {
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

    public List<Feature> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public Response(String text, String pinyin, String featureId, String userName, Score score, List<Feature> featureList) {
        this.text = text;
        this.pinyin = pinyin;
        this.featureId = featureId;
        this.userName = userName;
        this.score = score;
        this.featureList = featureList;
    }

    @Override
    public String toString() {
        return "Response{" +
                "text='" + text + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", featureId='" + featureId + '\'' +
                ", userName='" + userName + '\'' +
                ", score=" + score +
                ", featureList=" + featureList +
                '}';
    }
}
