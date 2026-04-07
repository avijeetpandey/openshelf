package com.avijeet.openshelf.service;

import com.avijeet.openshelf.dto.BookRequestDto;
import com.avijeet.openshelf.dto.BookResponseDto;
import com.avijeet.openshelf.dto.mappers.BookMapper;
import com.avijeet.openshelf.entities.Author;
import com.avijeet.openshelf.entities.Book;
import com.avijeet.openshelf.exceptions.BookNotFoundException;
import com.avijeet.openshelf.repository.AuthorRepository;
import com.avijeet.openshelf.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository,
                       BookMapper bookMapper,
                       AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
    }

    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).toList();
    }

    public BookResponseDto findBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book by id not found"));
        return bookMapper.toDto(book);
    }

    public BookResponseDto addBook(BookRequestDto dto) {
        // find the author is valid or not
        Author author = authorRepository.findById(dto.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        // map dto to entity
        Book book = bookMapper.toEntity(dto);
        book.setAuthor(author);
        book.setAvailableCopies(dto.totalCopies());

        log.info("Book with id {} is added successfully", book.getId());

        return bookMapper.toDto(bookRepository.save(book));
    }

    public BookResponseDto updateBookCopies(Long id, Integer newTotal) {
        Book book = bookRepository.findById(id)
                                    .orElseThrow(() -> new RuntimeException("Book not found"));

        int diff = newTotal - book.getTotalCopies();
        book.setTotalCopies(newTotal);
        book.setAvailableCopies(book.getAvailableCopies() + diff);

        log.info("Book with id {} is updated successfully", book.getId());

        return bookMapper.toDto(bookRepository.save(book));
    }
}
