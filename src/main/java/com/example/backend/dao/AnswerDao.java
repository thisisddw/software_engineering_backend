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
}
