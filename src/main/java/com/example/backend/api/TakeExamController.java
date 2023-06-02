package com.example.backend.api;

import com.example.backend.model.Answer;
import com.example.backend.service.TakeExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public String submit(@RequestBody List<Answer> answerList) {
        boolean b = takeExamService.submit(answerList, true);
        return b ? "success" : "failure";
    }
}
