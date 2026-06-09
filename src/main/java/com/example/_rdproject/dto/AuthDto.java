package com.example._rdproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthDto {
    @Getter
    public static class SignupRequest {
        private String login_id;
        private String password;
        private String nickname;
        private String preferred_partner_gender; // "male" or "female"
    }

    @Getter
    @Builder
    public static class SignupResponse {
        private Long user_id;
        private String login_id;
        private String nickname;
    }
    @Getter
    public static class LoginRequest {
        private String login_id;
        private String password;
    }

    @Getter
    @Builder
    public static class LoginResponse {
        private Long user_id;
        private String nickname;
    }
}