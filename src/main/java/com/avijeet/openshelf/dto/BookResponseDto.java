package com.avijeet.openshelf.dto;

public record BookResponseDto (
        Long id,
        String title,
        String isbn,
        String authorName,
        String genre
){}

