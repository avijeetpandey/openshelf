package com.avijeet.openshelf.controller;

import com.avijeet.openshelf.constants.Constants;
import com.avijeet.openshelf.dto.AuthorRequestDTO;
import com.avijeet.openshelf.dto.AuthorResponseDTO;
import com.avijeet.openshelf.response.ApiResponse;
import com.avijeet.openshelf.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController extends BaseController {
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<ApiResponse<AuthorResponseDTO>> create(@Valid @RequestBody AuthorRequestDTO dto) {
        AuthorResponseDTO response = authorService.createAuthor(dto);
        return ok(Constants.SUCCESS_MESSAGE, response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponseDTO>> getById(@PathVariable Long id) {
        return ok(Constants.SUCCESS_MESSAGE, authorService.getAuthorById(id));
    }
}
