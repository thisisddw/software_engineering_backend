package com.example.backend.model;

public class Answer {
    private Long userId;
    private Long questionId;
    private String answer;
    private Long score;

    public Answer() {
    }

    public Answer(Long userId, Long questionId, String answer, Long score) {
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
        this.score = score;
    }

    public boolean check() {
        return userId != null && questionId != null && answer != null;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
