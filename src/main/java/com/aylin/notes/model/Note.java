package com.aylin.notes.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Note {

    @Id
    private String id = null;
    @NonNull
    public String content;
    @NonNull
    public String colour;

    public String fontColour;

    @CreatedDate
    public LocalDateTime created;
}
