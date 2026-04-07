package com.avijeet.openshelf.controller;

import com.avijeet.openshelf.constants.Constants;
import com.avijeet.openshelf.dto.AuthResponseDto;
import com.avijeet.openshelf.dto.LoginRequestDto;
import com.avijeet.openshelf.response.ApiResponse;
import com.avijeet.openshelf.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@Slf4j
public class AuthController extends BaseController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDto>> login(@RequestBody LoginRequestDto requestDto) {
        return ok(Constants.SUCCESS_MESSAGE, authService.authenticate(requestDto));
    }
}
