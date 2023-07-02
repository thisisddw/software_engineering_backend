package com.example.backend.api;

import com.example.backend.dao.QuestionDao;
import com.example.backend.model.Question;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionDao questionDao;

    @PutMapping("/add")
    public ResponseEntity<String> addQuestion(HttpServletRequest req, @RequestBody Question question) {
        if (!req.getSession().getAttribute("type") .equals( "teacher")) {
            return new ResponseEntity<String>("failure", HttpStatus.FORBIDDEN);
        }
        if (questionDao.addQuestion(question) == 1) return new ResponseEntity<String>("success", HttpStatus.OK);
        else return new ResponseEntity<String>( "failure",HttpStatus.OK);
    }

}
