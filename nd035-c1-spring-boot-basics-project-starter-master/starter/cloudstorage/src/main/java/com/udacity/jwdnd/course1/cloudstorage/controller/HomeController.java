package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller()
public class HomeController {
@Autowired
private FileService fileService;
@Autowired
private UserService userService;

public void HomeController(FileService fileService, UserService userService)
{
    this.fileService = fileService;
    this.userService = userService;
}
    @RequestMapping("/home")
    String homeView(Authentication authentication, Model model){
        String userName = authentication.getName();
        Integer UID = userService.getUserId(userName);
        List<File> files = fileService.getFiles(UID);
        model.addAttribute("files", files);
        return "home";
    }

}
