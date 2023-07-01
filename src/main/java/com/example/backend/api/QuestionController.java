package com.example.backend.api;

import com.example.backend.dao.QuestionDao;
import com.example.backend.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionDao questionDao;

    @PutMapping("/add")
    public  void addQuestion(@RequestParam Question question) {
        questionDao.addQuestion(question);
    }

}
