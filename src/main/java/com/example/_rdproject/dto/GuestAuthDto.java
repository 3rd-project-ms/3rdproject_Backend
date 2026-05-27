package com.example._rdproject.dto;

import lombok.*;

public class GuestAuthDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String guest_id;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ResponseData {
        private Long user_id;
        private String nickname;
        private String current_level;
        private Integer continuous_days;
        private Boolean is_new_user;
    }
}