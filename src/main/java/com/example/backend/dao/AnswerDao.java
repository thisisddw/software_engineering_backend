package com.example.backend.dao;

import com.example.backend.model.Answer;
import com.example.backend.model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AnswerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    class BigQuestionMapper implements RowMapper<Map<String,String>> {
        @Override
        public Map<String,String> mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Map.of(
                    "id", rs.getString("id"),
                    "user_id", rs.getString("user_id"),
                    "answer", rs.getString("answer"),
                    "desc", rs.getString("desc"),
                    "max_score", rs.getString("max_score")
            );
        }
    }

    public List<Answer> getAnswerByUserExam(Long userId, Long examId) {
        return jdbcTemplate.query("select user_id, question_id, answer, score from answer a, question q " +
                        "where a.question_id = q.id and a.user_id = ? and q.exam_id = ?",
                new BeanPropertyRowMapper<>(Answer.class),
                userId, examId);
    }

    /* Insert or update answer. */
    public void setAnswer(Answer answer) {
        List<Answer> answerList = jdbcTemplate.query("select * from answer where user_id = ? and question_id = ?",
                new BeanPropertyRowMapper<>(Answer.class),
                answer.getUserId(), answer.getQuestionId());
        if (answerList.isEmpty()) {
            jdbcTemplate.update("insert into answer(user_id, question_id, answer, score) " +
                            "values (?, ?, ?, ?)", answer.getUserId(), answer.getQuestionId(),
                    answer.getAnswer(), answer.getScore());
            return;
        }
        jdbcTemplate.update("update answer set answer = ?, score = ? where " +
                        "user_id = ? and question_id = ?", answer.getAnswer(), answer.getScore(),
                answer.getUserId(), answer.getQuestionId());
    }

    public boolean updateScore(Long userId, Long questionId, Long score) {
        int update = jdbcTemplate.update("update answer set score = ? where " +
                "user_id = ? and question_id = ?", score, userId, questionId);
        return update == 1;
    }

    public List<Map<String,String>> getBigQuestionInfo(Long examId) {
        return jdbcTemplate.query("select q.id, q.desc, q.max_score, a.answer, a.user_id from question q, answer a " +
                        "where q.id = a.question_id and q.exam_id = ? and q.is_choice = false",
                new BigQuestionMapper(), examId);

    }
}
