package com.avijeet.openshelf.dto.mappers;

import com.avijeet.openshelf.dto.AuthorRequestDTO;
import com.avijeet.openshelf.dto.AuthorResponseDTO;
import com.avijeet.openshelf.entities.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorResponseDTO toDto(Author author);
    Author toEntity(AuthorRequestDTO authorRequestDTO);
}
