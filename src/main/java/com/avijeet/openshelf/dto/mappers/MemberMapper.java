package com.avijeet.openshelf.dto.mappers;

import com.avijeet.openshelf.dto.MemberRequestDto;
import com.avijeet.openshelf.dto.MemberResponseDto;
import com.avijeet.openshelf.entities.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    @Mapping(target = "fullName", expression = "java(member.getFirstName() + \" \" + member.getLastName())")
    MemberResponseDto toDto(Member member);

    Member toEntity(MemberRequestDto dto);
}
