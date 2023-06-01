package com.example.backend.service;

import com.example.backend.dao.SignUpInfoDao;
import com.example.backend.model.SignUpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class SignUpService {
    @Autowired
    private SignUpInfoDao signUpInfoDao;

    public List<SignUpInfo> getSignUpInfoByUserId(Long userId) {
        return signUpInfoDao.getSignUpInfoByUserId(userId);
    }

    public int signUp(Long userId, Long examId) {
        return signUpInfoDao.addSignUpInfo(userId, examId);
    }

    public int setExamFinish(SignUpInfo signUpInfo) {
        return signUpInfoDao.setExamFinish(signUpInfo.getUserId(), signUpInfo.getExamId());
    }
}
