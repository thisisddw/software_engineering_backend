package com.example.backend.service;

import com.example.backend.dao.UserDao;
import com.example.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User tryLogin(String name, String pwd) {
        User user = userDao.getUserByName(name);
        if (user != null && user.getPassword().equals(pwd))
            return user;
        return null;
    }

    public int register(User user) {
        if(userDao.getUserByName(user.getName()) != null)
            return -1;
        userDao.addUser(user);
        return 0;
    }
}
