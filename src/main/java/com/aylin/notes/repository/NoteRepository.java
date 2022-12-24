package com.aylin.notes.repository;

import com.aylin.notes.model.Note;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface NoteRepository extends ReactiveMongoRepository<Note,String> {
}
