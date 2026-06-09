package com.example._rdproject.controller;

import com.example._rdproject.dto.AuthDto;
import com.example._rdproject.dto.GuestAuthDto;
import com.example._rdproject.dto.common.CommonResponse;

import com.example._rdproject.service.AuthService;
import com.example._rdproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth & User Manager", description = "게스트 로그인 및 유저 프로필 관련 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @Operation(summary = "게스트 로그인 및 초기 진입", description = "기기 고유 UUID를 기반으로 소셜 로그인 없이 유저를 식별 및 가입 처리합니다.")
    @PostMapping("/guest")
    public CommonResponse<GuestAuthDto.GuestLoginResponseData> guestLogin(@RequestBody GuestAuthDto.GuestLoginRequest request) {

        GuestAuthDto.GuestLoginResponseData responseData = userService.loginOrCreateGuest(request);

        return CommonResponse.success("게스트 로그인이 완료되었습니다.", responseData);
    }
    @Operation(summary = "자체 회원가입", description = "ID/PW 기반으로 신규 회원을 등록합니다.")
    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<AuthDto.SignupResponse>> signup(@RequestBody AuthDto.SignupRequest request) {
        AuthDto.SignupResponse response = authService.signup(request);
        return ResponseEntity.ok(CommonResponse.success("회원가입이 완료되었습니다. 로그인을 진행해주세요.", response));
    }

    @Operation(summary = "자체 로그인", description = "ID/PW 기반으로 로그인을 수행합니다.")
    @PostMapping("/login")
    public ResponseEntity<CommonResponse<AuthDto.LoginResponse>> login(@RequestBody AuthDto.LoginRequest request) {
        AuthDto.LoginResponse response = authService.login(request);
        return ResponseEntity.ok(CommonResponse.success("로그인에 성공했습니다.", response));
    }
}