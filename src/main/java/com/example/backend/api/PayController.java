package com.example.backend.api;

import com.example.backend.model.Exam;
import com.example.backend.model.SignUpInfo;
import com.example.backend.service.ExamService;
import com.example.backend.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pay")
public class PayController {
    @Autowired
    private ExamService examService;
    @GetMapping
    public Exam getExam(@RequestParam("examId") Long examId) {
        //User=examService.getUserById(id);
        //SignUpInfo signUpInfo = signUpService.getSignUpInfo
        return examService.getExamById(examId);
    }
}
