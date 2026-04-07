package com.avijeet.openshelf.controller;

import com.avijeet.openshelf.constants.Constants;
import com.avijeet.openshelf.dto.BookRequestDto;
import com.avijeet.openshelf.dto.BookResponseDto;
import com.avijeet.openshelf.response.ApiResponse;
import com.avijeet.openshelf.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController extends BaseController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookResponseDto>>> getAllBooks() {
        return ok(Constants.SUCCESS_MESSAGE, bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDto>> getById(@Valid @PathVariable Long id) {
        BookResponseDto response = bookService.findBookById(id);
        return ok(Constants.SUCCESS_MESSAGE, response);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BookResponseDto>> addBook(@Valid @RequestBody BookRequestDto dto) {
        BookResponseDto response = bookService.addBook(dto);
        return ok(Constants.SUCCESS_MESSAGE, response);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<BookResponseDto>> updateBookCopies(@RequestBody Long id, @RequestBody Integer totalCount) {
        BookResponseDto updatedCopies = bookService.updateBookCopies(id,totalCount);
        return ok(Constants.SUCCESS_MESSAGE, updatedCopies);
    }
}
