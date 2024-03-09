package com.bqarlson.school.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/page-not-found")
    public ResponseEntity<String> pageNotFound() {
        return ResponseEntity.ok("PageNotFound");
    }

}
