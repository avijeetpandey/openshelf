package com.avijeet.openshelf.service;

import com.avijeet.openshelf.dto.AuthorRequestDTO;
import com.avijeet.openshelf.dto.AuthorResponseDTO;
import com.avijeet.openshelf.dto.mappers.AuthorMapper;
import com.avijeet.openshelf.entities.Author;
import com.avijeet.openshelf.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO) {
        Author author = authorMapper.toEntity(authorRequestDTO);
        log.info("Author added with details {}", author.getId());
        return authorMapper.toDto(authorRepository.save(author));
    }

    public List<AuthorResponseDTO> getAllAuthors() {
        return authorRepository.findAll().stream().map(authorMapper::toDto).toList();
    }

    public AuthorResponseDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author with id not found"));
        return authorMapper.toDto(author);
    }
}
