package com.avijeet.openshelf.service;

import com.avijeet.openshelf.dto.MemberRequestDto;
import com.avijeet.openshelf.dto.MemberResponseDto;
import com.avijeet.openshelf.dto.mappers.MemberMapper;
import com.avijeet.openshelf.entities.Member;
import com.avijeet.openshelf.enums.Role;
import com.avijeet.openshelf.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto addMember(MemberRequestDto dto) {
        if(memberRepository.existsByEmail(dto.email())) {
            log.error("Member with id {} already exists", dto.email());
            throw new RuntimeException("Member already exists");
        }

        Member member = memberMapper.toEntity(dto);
        member.setPassword(passwordEncoder.encode(dto.password()));

        member.setRole(Role.MEMBER);

        log.info("Member with id {} added successfully", member.getId());

        return memberMapper.toDto(memberRepository.save(member));
    }

    public MemberResponseDto getMemberByEmail(String email) {
        if(!memberRepository.existsByEmail(email)) {
            throw new RuntimeException("Member with email {} is not found");
        }
        return memberMapper.toDto(memberRepository.findByEmail(email));
    }
}
