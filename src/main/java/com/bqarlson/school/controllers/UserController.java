package com.bqarlson.school.controllers;

import com.bqarlson.school.entities.UserDetails;
import com.bqarlson.school.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/page-not-found")
    public ResponseEntity<String> pageNotFound() {
        return ResponseEntity.ok("PageNotFound");
    }

    @GetMapping("/user/login")
    public ResponseEntity<UserDetails> userLogin(HttpServletRequest request) throws IOException {
        return userService.userLogin(request);
    }

}
