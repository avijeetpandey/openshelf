package com.avijeet.openshelf.dto.mappers;

import com.avijeet.openshelf.dto.BookRequestDto;
import com.avijeet.openshelf.dto.BookResponseDto;
import com.avijeet.openshelf.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(source = "author.name", target = "authorName")
    BookResponseDto toDto(Book book);

    @Mapping(target = "author", ignore = true)
    Book toEntity(BookRequestDto dto);
}
