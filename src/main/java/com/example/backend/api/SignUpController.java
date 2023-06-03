package com.example.backend.api;

import com.example.backend.dao.SignUpInfoDao;
import com.example.backend.model.Exam;
import com.example.backend.model.SignUpInfo;
import com.example.backend.model.User;
import com.example.backend.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sign_up")
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public int signUp(@RequestBody SignUpInfo signUpInfo) {
        return signUpService.signUp(signUpInfo.getUserId(), signUpInfo.getExamId());
    }
    @GetMapping
    public List<SignUpInfo> getSignUpInfoByUserId(@RequestParam("id") Long id) {
        return signUpService.getSignUpInfoByUserId(id);
    }
    @GetMapping("/exam")
    public List<Map<String, String>> getSignUpWithExamByUserId(@RequestParam("id") Long id) {
        List<Object[]> signExamList = signUpService.getSignUpInfoWithExamByUserId(id);
        List<Map<String, String>> ret = new ArrayList<>();
        for (Object[] se: signExamList) {
            SignUpInfo sign = (SignUpInfo) se[0];
            Exam exam = (Exam) se[1];
            ret.add(Map.ofEntries(
                    Map.entry("userId", sign.getUserId().toString()),
                    Map.entry("examId", sign.getExamId().toString()),
                    Map.entry("finish", sign.getFinish() ? "1" : "0"),
                    Map.entry("examName", exam.getName()),
                    Map.entry("examDesc", exam.getDesc())
            ));
        }
        return ret;
    }
    @GetMapping("/finished")
    public List<Map<String, String>> getFinishedSignUpByExamId(@RequestParam("id") Long id) {
        List<Object[]> signUserList = signUpService.getFinishedSignUpInfoWithUserByExamId(id);
        List<Map<String, String>> ret = new ArrayList<>();
        for (Object[] su: signUserList) {
            SignUpInfo sign = (SignUpInfo) su[0];
            User user = (User) su[1];
            ret.add(Map.ofEntries(
                    Map.entry("userId", sign.getUserId().toString()),
                    Map.entry("examId", sign.getExamId().toString()),
                    Map.entry("finish", sign.getFinish() ? "1" : "0"),
                    Map.entry("userName", user.getName())
            ));
        }
        return ret;
    }
    @PutMapping
    public int setExamFinish(@RequestBody SignUpInfo signUpInfo) {
        return signUpService.setExamFinish(signUpInfo);
    }
}
