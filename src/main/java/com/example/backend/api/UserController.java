package com.example.backend.api;

import com.example.backend.dao.UserDao;
import com.example.backend.model.User;
import com.example.backend.service.UserService;
//import com.example.backend.util.SessionList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
//import servlet cookie
import jakarta.servlet.http.Cookie;
@RestController
@RequestMapping("/api/user")
public class UserController{
    @Autowired
    private UserService userService;
    //@Autowired
   // private SessionList sessionList;
    @GetMapping("/login")
    public  User tryLogin(
                         HttpServletRequest request,
                         @RequestParam("name") String name,
                         @RequestParam("pwd") String pwd) {
        HttpSession HttpSession = request.getSession();

      //  OutputStream outputStream;
        User user = userService.tryLogin(name, pwd);
        if(user!=null){
            HttpSession.setAttribute("name", name);
            HttpSession.setAttribute("id", user.getId());
            HttpSession.setAttribute("type", user.getType());
        }
        return user;
    }

    @PostMapping("/register")
    public int register(@RequestBody User user) {
        return userService.register(user);
    }
}
