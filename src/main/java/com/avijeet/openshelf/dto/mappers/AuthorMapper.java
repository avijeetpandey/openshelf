package com.avijeet.openshelf.dto.mappers;

import com.avijeet.openshelf.dto.AuthorRequestDTO;
import com.avijeet.openshelf.dto.AuthorResponseDTO;
import com.avijeet.openshelf.entities.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorResponseDTO toDto(Author author);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Author toEntity(AuthorRequestDTO authorRequestDTO);
}
