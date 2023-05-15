package com.jlw.ssp.entity;

public class Score {

    private String accuracyScore;
    private String emotionScore;
    private String fluencyScore;
    private String totalScore;

    public Score() {
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

    public Score(String accuracyScore, String emotionScore, String fluencyScore, String totalScore) {
        this.accuracyScore = accuracyScore;
        this.emotionScore = emotionScore;
        this.fluencyScore = fluencyScore;
        this.totalScore = totalScore;
    }
}
