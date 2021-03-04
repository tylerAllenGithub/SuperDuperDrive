package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.*;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
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
@Autowired
private NoteService noteService;
@Autowired
private CredentialService credentialService;
@Autowired
private EncryptionService encryptionService;

public void HomeController(FileService fileService, UserService userService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService)
{
    this.fileService = fileService;
    this.userService = userService;
    this.noteService = noteService;
    this.credentialService = credentialService;
    this.encryptionService = encryptionService;
}
    @RequestMapping("/home")
    String homeView(NoteForm noteForm, CredentialForm credentialForm, Authentication authentication, Model model){
        String userName = authentication.getName();
        Integer UID = userService.getUserId(userName);
        List<File> files = fileService.getFiles(UID);
        List<Note> notes = noteService.getNotes(UID);
        List<Credential> credentials = credentialService.getCredentials(UID);
        model.addAttribute("files", files);
        model.addAttribute("notes", notes);
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("credentials", credentials);
        return "home";
    }

}
