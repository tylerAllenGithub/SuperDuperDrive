package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/login")
public class LoginController {
boolean error;
boolean logout;

    @PostConstruct
    private void init()
    {
        error = false;
        logout = false;
    }
    @GetMapping
    public String loginView(){
        return "login";
    }
}
