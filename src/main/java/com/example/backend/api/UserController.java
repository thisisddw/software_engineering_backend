package com.example.backend.api;

import com.example.backend.dao.UserDao;
import com.example.backend.model.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public User tryLogin(@RequestParam("name") String name,
                         @RequestParam("pwd") String pwd) {
        return userService.tryLogin(name, pwd);
    }

    @PostMapping("/register")
    public int register(@RequestBody User user) {
        return userService.register(user);
    }
}
