package com.example.backend.api;

import com.example.backend.model.Answer;
import com.example.backend.service.TakeExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/take_exam")
public class TakeExamController {
    @Autowired
    private TakeExamService takeExamService;

    @GetMapping
    public List<Answer> getAnswerSheet(@RequestParam("uid") Long userId,
                                       @RequestParam("eid") Long examId) {
        return takeExamService.getAnswerSheet(userId, examId);
    }
}
