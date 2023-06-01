package com.example.backend.model;

public class Question {
    private Long id;
    private Long examId;
    private Long number;
    private Boolean isChoice;
    private String desc;
    private String stdAnswer;
    private Long maxScore;

    public Question() {
    }

    public Question(Long id, Long examId, Long number, Boolean isChoice, String desc, String stdAnswer, Long maxScore) {
        this.id = id;
        this.examId = examId;
        this.number = number;
        this.isChoice = isChoice;
        this.desc = desc;
        this.stdAnswer = stdAnswer;
        this.maxScore = maxScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Boolean getIsChoice() {
        return isChoice;
    }

    public void setIsChoice(Boolean isChoice) {
        this.isChoice = isChoice;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStdAnswer() {
        return stdAnswer;
    }

    public void setStdAnswer(String stdAnswer) {
        this.stdAnswer = stdAnswer;
    }

    public Long getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Long maxScore) {
        this.maxScore = maxScore;
    }
}
