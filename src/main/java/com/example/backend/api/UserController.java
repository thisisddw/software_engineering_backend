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
    public  User tryLogin(HttpServletResponse response,
                         HttpServletRequest request,
                         @RequestParam("name") String name,
                         @RequestParam("pwd") String pwd) {
        HttpSession HttpSession = request.getSession();

      //  OutputStream outputStream;
        User user = userService.tryLogin(name, pwd);
//        try {
//            //outputStream = response.getOutputStream();
//            //if(user!=null)outputStream.write(user.toString().getBytes());
//            if(user!=null)
//            //outputStream.flush();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        String sessionID = HttpSession.getId();
        if(user!=null){
            HttpSession.setAttribute("name", name);
            HttpSession.setAttribute("id", user.getId());
            HttpSession.setAttribute("type", user.getType());
            //sessionList.put(sessionID, HttpSession);
        }
        return user;
//        if(!sessionList.containsKey(sessionID)&&user!=null){
//            Cookie cookie = new Cookie("sessionID", sessionID);
//            cookie.setMaxAge(60 * 60 * 24 * 7);
//            sessionList.put(sessionID, HttpSession);
//            response.addCookie(cookie);
//        }
    }
//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/login")
//    public User tryLogin(@RequestParam("name") String name,
//                         @RequestParam("pwd") String pwd) {
//        return userService.tryLogin(name, pwd);
//    }
//
    @PostMapping("/register")
    public int register(@RequestBody User user) {
        return userService.register(user);
    }
}
