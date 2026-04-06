package com.avijeet.openshelf.dto;

public record BookRequestDto(
        String title,
        String isbn,
        Long authorId,
        String genre,
        Integer totalCopies
) {}
