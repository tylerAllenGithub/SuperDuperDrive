package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper)
    {
        this.noteMapper = noteMapper;
    }
    public Note getNote(Integer noteid)
    {
        return noteMapper.getNoteFromId(noteid);
    }
    public Note getNoteFromTitle(String notetitle)
    {
        return noteMapper.getNoteFromTitle(notetitle);
    }
    public void addNote(Note note)
    {
        noteMapper.insert(note);
    }
    public void editNote(Note note)
    {
        noteMapper.editNote(note);
    }
    public void deleteNote(Integer noteId)
    {
        noteMapper.delete(noteId);
    }
    public List<Note> getNotes(Integer userId)
    {
        return noteMapper.getNotes(userId);
    }


}
