package com.avijeet.openshelf.dto;

public record AuthResponseDto(
        String accessToken,
        String email,
        String role
) {}
