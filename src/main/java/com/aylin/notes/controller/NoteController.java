package com.aylin.notes.controller;

import com.aylin.notes.model.Note;
import com.aylin.notes.repository.NoteRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "http://localhost:3000")
public class NoteController {
    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/{id}")
    private Mono<Note> getNoteById(@PathVariable String id) {
        return noteRepository.findById(id);
    }

    @GetMapping
    private Flux<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @PostMapping("/insert")
    private Mono<Note> insertNote(@RequestBody Note note) {
        note.created = LocalDateTime.now(ZoneOffset.UTC);
        return noteRepository.save(note);
    }
}
