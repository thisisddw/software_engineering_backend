package com.example.backend.dao;

import com.example.backend.model.Exam;
import com.example.backend.model.SignUpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SignUpInfoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SignUpInfo> getSignUpInfoByUserId(Long id) {
        return jdbcTemplate.query("select * from sign_up where user_id = ?",
                new BeanPropertyRowMapper<>(SignUpInfo.class), id);
    }

    class SignUpExamRowMapper implements RowMapper<Object[]> {
        @Override
        public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
            SignUpInfo sign = new SignUpInfo(
                    rs.getLong(1),
                    rs.getLong(2),
                    rs.getBoolean(3)
            );
            Exam exam = new Exam(
                    rs.getLong(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getFloat(7)
            );
            return new Object[]{sign, exam};
        }
    }

    public List<Object[]> getSignUpInfoWithExamByUserId(Long id) {
        return jdbcTemplate.query("select * from sign_up s, exam e " +
                        "where s.user_id = ? and s.exam_id = e.id",
                new SignUpExamRowMapper(), id);
    }

    public List<SignUpInfo> getSignUpInfoByExamId(Long id) {
        return jdbcTemplate.query("select * from sign_up where exam_id = ?",
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
