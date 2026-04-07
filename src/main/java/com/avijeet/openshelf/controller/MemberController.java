package com.avijeet.openshelf.controller;

import com.avijeet.openshelf.constants.Constants;
import com.avijeet.openshelf.dto.MemberRequestDto;
import com.avijeet.openshelf.dto.MemberResponseDto;
import com.avijeet.openshelf.response.ApiResponse;
import com.avijeet.openshelf.service.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@AllArgsConstructor
public class MemberController extends BaseController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse<MemberResponseDto>> addMember(@Valid @RequestBody MemberRequestDto dto) {
        MemberResponseDto response = memberService.addMember(dto);
        return ok(Constants.SUCCESS_MESSAGE, response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<MemberResponseDto>> getMemberByEmail(@RequestParam String email) {
        return ok(Constants.SUCCESS_MESSAGE, memberService.getMemberByEmail(email));
    }
}
