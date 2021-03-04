package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    public void NoteController(NoteService noteService, UserService userService)
    {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public String addNote(NoteForm noteForm, Authentication authentication, Model model){
        Note note = new Note();
        String username = authentication.getName();
        Integer userid = userService.getUserId(username);
        String title = noteForm.getNotetitle();
        note.setNotetitle(title);
        String description = noteForm.getNotedescription();
        note.setNotedescription(description);
        note.setUserid(userid);
        Integer noteId = noteForm.getNoteid();
        if (noteId != null) {
            note.setNoteid(noteId);
            noteService.editNote(note);
        }
        else {
            noteService.addNote(note);
        }
        model.addAttribute("notes", noteService.getNotes(userid));

        model.addAttribute("success", "true");
        model.addAttribute("failure", "false");

        return "result";
    }
    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Authentication authentication, Model model) {
        noteService.deleteNote(noteId);
        Integer userid = userService.getUserId(authentication.getName());
        model.addAttribute("notes", noteService.getNotes(userid));
        if(noteService.getNote(noteId)==null) {
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
