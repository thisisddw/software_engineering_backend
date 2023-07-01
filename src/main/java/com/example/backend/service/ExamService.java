package com.example.backend.service;

import com.example.backend.dao.ExamDao;
import com.example.backend.dao.QuestionDao;
import com.example.backend.model.Exam;
import com.example.backend.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamDao examDao;
    @Autowired
    private QuestionDao questionDao;

    public List<Exam> getAllExams() {
        return examDao.getAllExams();
    }

    public List<Question> getExamContentById(Long exam_id) {
        List<Question> questions = questionDao.getQuestionsByExam(exam_id);
        questions.sort((q1, q2) -> {
            return (int) (q1.getNumber() - q2.getNumber());
        });
        return questions;
    }
    public Exam getExamById(Long id) {
        return examDao.getExamById(id);
    }
//    public Exam getExamByIdAndUId(Long id, Long uId) {
//        return examDao.getExamById(name);
//    }
}
