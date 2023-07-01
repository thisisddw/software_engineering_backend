package com.example.backend.dao;

import com.example.backend.model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExamDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    class ExamMapper implements RowMapper<Exam> {
        @Override
        public Exam mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Exam(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("desc"),
                    rs.getFloat("price")
            );
        }
    }

    public List<Exam> getAllExams() {
        return jdbcTemplate.query("select * from exam", new ExamMapper());
    }
    public Exam getExamById(Long id) {
        List<Exam> query = jdbcTemplate.query("select * from exam where id = ?",
                new ExamMapper(), id);
        return query.isEmpty() ? null : query.get(0);
    }
}
