package com.jlw.ssp.entity;

public class Result {

    private String text;
    private String pinyin;
    private String maxScoreFeatureId;
    private String thisFeatureId;
    private Score score;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMaxScoreFeatureId() {
        return maxScoreFeatureId;
    }

    public void setMaxScoreFeatureId(String maxScoreFeatureId) {
        this.maxScoreFeatureId = maxScoreFeatureId;
    }

    public String getThisFeatureId() {
        return thisFeatureId;
    }

    public void setThisFeatureId(String thisFeatureId) {
        this.thisFeatureId = thisFeatureId;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Result() {
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Result(String text, String pinyin, String maxScoreFeatureId, String thisFeatureId, Score score) {
        this.text = text;
        this.pinyin = pinyin;
        this.maxScoreFeatureId = maxScoreFeatureId;
        this.thisFeatureId = thisFeatureId;
        this.score = score;
    }
}
