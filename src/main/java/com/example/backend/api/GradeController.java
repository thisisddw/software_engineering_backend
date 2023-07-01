package com.example.backend.api;

import com.example.backend.service.GradeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @PutMapping
    public  ResponseEntity<String> setScore(HttpServletRequest req, @RequestBody Map<String, Long> params) {
        if (req.getSession().getAttribute("type") != "teacher") {
            return new ResponseEntity<String>("failure", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<String>(gradeService.setScore(params.get("userId"), params.get("questionId"), params.get("score")), HttpStatus.OK);
    }

    /**
     * @return [0]: actual grade of choice questions
     * [1]: actual grade of other questions
     * [2]: max grade of choice questions
     * [3]: max grade of other questions
     */
    @GetMapping
    public ResponseEntity<Object[]>  getScore(HttpServletRequest req, @RequestParam("uid") Long userId,
                             @RequestParam("eid") Long examId) {
        if (req.getSession().getAttribute("id") != userId) {
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(gradeService.getGrade(userId, examId), HttpStatus.OK);
    }
}
