package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class LogoutController {
    @PostMapping("logout")
    String logout(Model model){
        model.addAttribute("logout",true);
        return "login";
    }
}
