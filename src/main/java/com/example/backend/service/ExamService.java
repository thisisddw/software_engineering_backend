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
        return questionDao.getQuestionsByExam(exam_id);
    }
}
