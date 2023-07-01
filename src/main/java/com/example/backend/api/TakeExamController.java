package com.example.backend.api;

import com.example.backend.model.Answer;
import com.example.backend.service.TakeExamService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/take_exam")
public class TakeExamController {
    @Autowired
    private TakeExamService takeExamService;

    @GetMapping
    public ResponseEntity<List<Answer>> getAnswerSheet(HttpServletRequest req, @RequestParam("uid") Long userId,
                                       @RequestParam("eid") Long examId) {
        if (req.getSession().getAttribute("id") != userId) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(takeExamService.getAnswerSheet(userId, examId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> submit(HttpServletRequest req,@RequestBody List<Answer> answerList) {
        for(Answer answer: answerList) {
            if(answer.getUserId()!=req.getSession().getAttribute("id")) return new ResponseEntity<>("failure", HttpStatus.FORBIDDEN);
        }
        boolean b = takeExamService.submit(answerList, true);
        String str=b ? "success" : "failure";
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
}
