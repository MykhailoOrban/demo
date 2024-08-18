package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        return new ResponseEntity<>(noteService.create(note), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.findById(id)
                .map(note -> new ResponseEntity<>(note, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(noteService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @Valid @RequestBody Note note) {
        return new ResponseEntity<>(noteService.update(id, note), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}