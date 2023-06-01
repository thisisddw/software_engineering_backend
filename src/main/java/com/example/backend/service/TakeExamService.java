package com.example.backend.service;

import com.example.backend.dao.AnswerDao;
import com.example.backend.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TakeExamService {
    @Autowired
    private AnswerDao answerDao;

    public List<Answer> getAnswerSheet(Long userId, Long examId) {
        return answerDao.getAnswerByUserExam(userId, examId);
    }
}
