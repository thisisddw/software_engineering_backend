package com.example.backend.service;

import com.example.backend.dao.SignUpInfoDao;
import com.example.backend.dao.UserDao;
import com.example.backend.model.SignUpInfo;
import com.example.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SignUpService {
    @Autowired
    private SignUpInfoDao signUpInfoDao;
    @Autowired
    private UserDao userDao;

    public List<SignUpInfo> getSignUpInfoByUserId(Long userId) {
        return signUpInfoDao.getSignUpInfoByUserId(userId);
    }

    public List<Object[]> getSignUpInfoWithExamByUserId(Long userId) {
        return signUpInfoDao.getSignUpInfoWithExamByUserId(userId);
    }

    public List<Object[]> getFinishedSignUpInfoWithUserByExamId(Long examId) {
        List<SignUpInfo> infoList = signUpInfoDao.getSignUpInfoByExamId(examId);
        List<Object[]> ret = new ArrayList<>();
        for (SignUpInfo signUpInfo : infoList) {
            if (signUpInfo.getFinish()) {
                User user = userDao.getUserById(signUpInfo.getUserId());
                ret.add(new Object[]{signUpInfo, user});
            }
        }
        return ret;
    }

    public int signUp(Long userId, Long examId) {
        return signUpInfoDao.addSignUpInfo(userId, examId);
    }

    public int setExamFinish(SignUpInfo signUpInfo) {
        return signUpInfoDao.setExamFinish(signUpInfo.getUserId(), signUpInfo.getExamId());
    }
}
