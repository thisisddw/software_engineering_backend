package com.example.backend.dao;

import com.example.backend.model.SignUpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SignUpInfoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SignUpInfo> getSignUpInfoByUserId(Long id) {
        return jdbcTemplate.query("select * from sign_up where user_id = ?",
                new BeanPropertyRowMapper<>(SignUpInfo.class), id);
    }
    public int addSignUpInfo(Long userId, Long examId) {
        return jdbcTemplate.update("insert into sign_up(user_id, exam_id, finish) values(?, ?, ?)",
                userId, examId, 0);
    }
    public int setExamFinish(Long userId, Long examId) {
        return jdbcTemplate.update("update sign_up set finish = 1 where user_id = ? and exam_id = ?",
                userId, examId);
    }
}
