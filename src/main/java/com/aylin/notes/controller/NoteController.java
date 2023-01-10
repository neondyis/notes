package com.aylin.notes.controller;

import com.aylin.notes.model.Note;
import com.aylin.notes.repository.NoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = {"http://localhost:3000","https://note-front-gamma.vercel.app"})
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

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<Void> deleteNote(@PathVariable String id) {
        return noteRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("NOTE_NOT_FOUND")))
                .flatMap(note -> noteRepository.deleteById(note.getId()));
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    private Mono<Note> editNote(@RequestBody Note note) {
        return noteRepository.findById(note.getId())
                .switchIfEmpty(Mono.error(new Exception("NOTE_NOT_FOUND")))
                .map(fetchedNote -> {
                    fetchedNote.setColour(note.colour);
                    fetchedNote.setContent(note.content);
                    fetchedNote.setFontColour(note.fontColour);
                            return fetchedNote;
                }).flatMap(noteRepository::save);
    }
}
