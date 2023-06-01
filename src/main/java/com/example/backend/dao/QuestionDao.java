package com.example.backend.dao;

import com.example.backend.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Question> getQuestionsByExam(Long exam_id) {
        return jdbcTemplate.query("select * from question where exam_id = ?",
                new BeanPropertyRowMapper<Question>(Question.class), exam_id);
    }
}
