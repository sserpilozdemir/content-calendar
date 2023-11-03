package com.serpilozdemir.contentcalendar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

public record Content(
        @Id
        Integer id,
        String title,
        @Column(value = "description")
        String desc,
        Status status,
        Type content_type,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url

){

}
