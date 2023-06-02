package com.example.backend.service;

import com.example.backend.dao.AnswerDao;
import com.example.backend.dao.ExamDao;
import com.example.backend.dao.QuestionDao;
import com.example.backend.model.Answer;
import com.example.backend.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GradeService {
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private QuestionDao questionDao;

    public String setScore(Long userId, Long questionId, Long score) {
        return answerDao.updateScore(userId, questionId, score) ? "success" : "failure";
    }

    /**
     * @return [0]: actual grade of choice questions
     * [1]: actual grade of other questions
     * [2]: max grade of choice questions
     * [3]: max grade of other questions
     */
    public Object[] getGrade(Long userId, Long examId) {
        List<Question> exam = questionDao.getQuestionsByExam(examId);
        long max1 = 0, max2 = 0;
        HashMap<Long, Boolean> qid2IsChoice = new HashMap<>();
        for (Question question : exam) {
            if (question.getIsChoice())
                max1 += question.getMaxScore();
            else
                max2 += question.getMaxScore();
            qid2IsChoice.put(question.getId(), question.getIsChoice());
        }
        List<Answer> answers = answerDao.getAnswerByUserExam(userId, examId);
        Long s1 = 0L, s2 = 0L;
        for (Answer answer : answers) {
            Long score = answer.getScore();
            if (qid2IsChoice.get(answer.getQuestionId()))
                s1 = (s1 != null && score != null) ? s1 + score : null;
            else
                s2 = (s2 != null && score != null) ? s2 + score : null;
        }
        return new Object[]{s1, s2, max1, max2};
    }
}
