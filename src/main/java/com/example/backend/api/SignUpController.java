package com.example.backend.api;

import com.example.backend.dao.SignUpInfoDao;
import com.example.backend.model.SignUpInfo;
import com.example.backend.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping
    public int setExamFinish(@RequestBody SignUpInfo signUpInfo) {
        return signUpService.setExamFinish(signUpInfo);
    }
}
