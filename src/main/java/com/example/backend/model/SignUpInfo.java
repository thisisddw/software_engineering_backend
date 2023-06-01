package com.example.backend.model;

public class SignUpInfo {
    private Long userId;
    private Long examId;
    private Boolean finish;

    public SignUpInfo() {
    }

    public SignUpInfo(Long userId, Long examId, Boolean finish) {
        this.userId = userId;
        this.examId = examId;
        this.finish = finish;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }
}
