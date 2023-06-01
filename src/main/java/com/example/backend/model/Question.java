package com.example.backend.model;

public class Question {
    private Long id;
    private Long exam_id;
    private Long number;
    private Boolean is_choice;
    private String desc;
    private String std_answer;
    private Long max_score;

    public Question() {
    }

    public Question(Long id, Long exam_id, Long number, Boolean is_choice, String desc, String std_answer, Long max_score) {
        this.id = id;
        this.exam_id = exam_id;
        this.number = number;
        this.is_choice = is_choice;
        this.desc = desc;
        this.std_answer = std_answer;
        this.max_score = max_score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExam_id() {
        return exam_id;
    }

    public void setExam_id(Long exam_id) {
        this.exam_id = exam_id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Boolean getIs_choice() {
        return is_choice;
    }

    public void setIs_choice(Boolean is_choice) {
        this.is_choice = is_choice;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStd_answer() {
        return std_answer;
    }

    public void setStd_answer(String std_answer) {
        this.std_answer = std_answer;
    }

    public Long getMax_score() {
        return max_score;
    }

    public void setMax_score(Long max_score) {
        this.max_score = max_score;
    }
}
