package com.example.backend.api;

import com.example.backend.model.Exam;
import com.example.backend.model.Question;
import com.example.backend.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping("/all")
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/content")
    public List<Question> getExamContent(@RequestParam Long id) {
        return examService.getExamContentById(id);
    }
}
