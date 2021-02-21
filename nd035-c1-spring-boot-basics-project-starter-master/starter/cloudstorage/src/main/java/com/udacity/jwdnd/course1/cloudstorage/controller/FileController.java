package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
public class FileController {
    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/uploadFile")
    String handleFileUpload(@RequestParam("fileUpload")MultipartFile fileUpload, Authentication authentication, Model model)
    {
        String username = authentication.getName();
        Integer UID = userService.getUserId(username);
        boolean result = fileService.addFile(fileUpload, UID);
        model.addAttribute("fileUploadSuccess", result);
        model.addAttribute("fileUploadFailure", !result);
        List<File> files = fileService.getFiles(UID);
        model.addAttribute("files", files);
        return "result";
    }

}
