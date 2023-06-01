package com.example.backend.dao;

import com.example.backend.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Answer> getAnswerByUserExam(Long userId, Long examId) {
        return jdbcTemplate.query("select user_id, question_id, answer, score from answer a, question q " +
                "where a.question_id = q.id and a.user_id = ? and q.exam_id = ?",
                new BeanPropertyRowMapper<>(Answer.class),
                userId, examId);
    }
}
