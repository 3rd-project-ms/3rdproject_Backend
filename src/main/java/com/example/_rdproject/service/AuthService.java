package com.example._rdproject.service;

import com.example._rdproject.domain.GenderType;
import com.example._rdproject.dto.AuthDto;
import com.example._rdproject.entity.User;
import com.example._rdproject.exception.DuplicateIdException;
import com.example._rdproject.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder; // 암호화 도구

    @Transactional
    public AuthDto.SignupResponse signup(AuthDto.SignupRequest request) {
        // 1. 아이디 중복 체크
        if (userRepository.findByLoginId(request.getLogin_id()).isPresent()) {
            throw new DuplicateIdException("이미 사용 중인 아이디입니다."); // 커스텀 예외 권장
        }

        // 2. 유저 생성 및 비밀번호 암호화
        User user = User.builder()
                .loginId(request.getLogin_id())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .preferredPartnerGender(GenderType.valueOf(request.getPreferred_partner_gender().toLowerCase()))
                .continuousDays(0)
                .build();

        User savedUser = userRepository.save(user);
        return AuthDto.SignupResponse.builder()
                .user_id(savedUser.getId())
                .login_id(savedUser.getLoginId())
                .nickname(savedUser.getNickname())
                .build();
    }
    @Transactional(readOnly = true)
    public AuthDto.LoginResponse login(AuthDto.LoginRequest request) {
        // 1. 유저 조회
        User user = userRepository.findByLoginId(request.getLogin_id())
                .orElseThrow(() -> new RuntimeException("아이디 또는 비밀번호가 일치하지 않습니다."));

        // 2. 비밀번호 일치 확인 (BCrypt)
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        // 3. 성공 시 응답 반환
        return AuthDto.LoginResponse.builder()
                .user_id(user.getId())
                .nickname(user.getNickname())
                .build();
    }
}
