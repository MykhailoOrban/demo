package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note create(Note note) {
        return noteRepository.save(note);
    }

    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note update(Long id, Note note) {
        Note existingNote = noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
        return noteRepository.save(existingNote);
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }
}