package com.avijeet.openshelf.service;

import com.avijeet.openshelf.dto.AuthResponseDto;
import com.avijeet.openshelf.dto.LoginRequestDto;
import com.avijeet.openshelf.entities.Member;
import com.avijeet.openshelf.repository.MemberRepository;
import com.avijeet.openshelf.response.ApiResponse;
import com.avijeet.openshelf.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthResponseDto authenticate(LoginRequestDto request) {
        Member member = memberRepository.findByEmail(request.email());
        if(member == null || !passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new RuntimeException("Invalid creds");
        }

        String token = jwtUtils.generateToken(member.getEmail());
        return new AuthResponseDto(token,member.getEmail(),member.getRole().name());
    }
}
