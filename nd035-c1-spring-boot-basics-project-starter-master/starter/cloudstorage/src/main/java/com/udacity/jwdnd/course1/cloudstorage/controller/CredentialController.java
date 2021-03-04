package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private UserService userService;
    @Autowired
    private EncryptionService encryptionService;

    public void NoteController(CredentialService credentialService, UserService userService, EncryptionService encryptionService)
    {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @PostMapping("/add")
    public String addNote(CredentialForm credentialForm, Authentication authentication, Model model){
        Credential credential = new Credential();
        String username = authentication.getName();
        Integer userid = userService.getUserId(username);
        String url = credentialForm.getUrl();
        credential.setUrl(url);
        String credname = credentialForm.getUsername();
        credential.setUsername(credname);
        String password = credentialForm.getPassword();

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credential.setKey(encodedKey);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setUserid(userid);
        Integer credId = credentialForm.getCredentialid();
        if (credId != null) {
            credential.setCredentialid(credId);
            credentialService.editCredential(credential);
        }
        else {
            credentialService.addCredential(credential);
        }
        model.addAttribute("credentials", credentialService.getCredentials(userid));

        model.addAttribute("success", "true");
        model.addAttribute("failure", "false");

        return "result";
    }
    @GetMapping("/delete/{credentialId}")
    public String deleteNote(@PathVariable Integer credentialId, Authentication authentication, Model model) {
        credentialService.deleteCredential(credentialId);
        Integer userid = userService.getUserId(authentication.getName());
        model.addAttribute("credentials", credentialService.getCredentials(userid));
        if(credentialService.getCredential(credentialId)==null) {
            model.addAttribute("success", true);
            model.addAttribute("failure", false);
            model.addAttribute("error", false);
        }
        else {
            model.addAttribute("success", false);
            model.addAttribute("failure", true);
            model.addAttribute("error", true);
        }
        return "result";
    }
}
