package com.example.backend.api;

import com.example.backend.model.Answer;
import com.example.backend.service.TakeExamService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/take_exam")
public class TakeExamController {
    @Autowired
    private TakeExamService takeExamService;

    @GetMapping
    public ResponseEntity<List<Answer>> getAnswerSheet(HttpServletRequest req, @RequestParam("uid") Long userId,
                                       @RequestParam("eid") Long examId) {
        if (!req.getSession().getAttribute("type").equals( "teacher")) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(takeExamService.getAnswerSheet(userId, examId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> submit(HttpServletRequest req,@RequestBody List<Answer> answerList) {
        for(Answer answer: answerList) {
            if(!answer.getUserId().equals(req.getSession().getAttribute("id"))) return new ResponseEntity<>("failure", HttpStatus.FORBIDDEN);
        }
        boolean b = takeExamService.submit(answerList, true);
        String str=b ? "success" : "failure";
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
    /*
     * @Description: 获取所有学生的大题答案
     * <大题号，学生id，学生答案，题目描述，最大分数>
     */
    @GetMapping("/big_question")
    public ResponseEntity<List<Map<String,String>>> getBigQuestionInfo(HttpServletRequest req,
                                       @RequestParam("eid") Long examId) {
        if (!req.getSession().getAttribute("type").equals( "teacher")) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(takeExamService.getBigQuestionInfo(examId), HttpStatus.OK);
    }
}
