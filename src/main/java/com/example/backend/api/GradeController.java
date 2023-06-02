package com.example.backend.api;

import com.example.backend.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @PutMapping
    public String setScore(@RequestBody Map<String, Long> params) {
        return gradeService.setScore(params.get("userId"), params.get("questionId"), params.get("score"));
    }

    /**
     * @return [0]: actual grade of choice questions
     * [1]: actual grade of other questions
     * [2]: max grade of choice questions
     * [3]: max grade of other questions
     */
    @GetMapping
    public Object[] getScore(@RequestParam("uid") Long userId,
                             @RequestParam("eid") Long examId) {
        return gradeService.getGrade(userId, examId);
    }
}
