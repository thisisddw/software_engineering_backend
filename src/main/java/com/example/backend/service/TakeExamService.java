package com.example.backend.service;

import com.example.backend.dao.AnswerDao;
import com.example.backend.dao.QuestionDao;
import com.example.backend.model.Answer;
import com.example.backend.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TakeExamService {
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private QuestionDao questionDao;

    public List<Answer> getAnswerSheet(Long userId, Long examId) {
        return answerDao.getAnswerByUserExam(userId, examId);
    }

    public boolean submit(List<Answer> answerList, boolean autoGrading) {
        for (Answer ans : answerList) {
            if (!ans.check()) return false;
            if (autoGrading) {
                Question question = questionDao.getQuestionById(ans.getQuestionId());
                if (question == null) return false;
                if (question.getIsChoice()) {
                    ans.setScore(ans.getAnswer().equals(question.getStdAnswer()) ? question.getMaxScore() : 0L);
                }
            }
            answerDao.setAnswer(ans);
        }
        return true;
    }

    public List<Map<String,String>> getBigQuestionInfo(Long examId) {
        return answerDao.getBigQuestionInfo(examId);
    }
}
