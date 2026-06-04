package com.example._rdproject.dto;

import com.example._rdproject.domain.AssignedLevel;
import com.example._rdproject.domain.TestType;
import com.example._rdproject.entity.User;
import lombok.*;

import java.util.List;

public class LevelTestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveRequest {
        private Long userId;
        private TestType testType; // SELECT 또는 TEST
        private AssignedLevel assignedLevel; // A1 ~ C2
        private Integer testScore; // 점수 (SELECT일 경우 null 가능)
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatusResponse {
        private Long userId;
        private User.CefrLevel currentLevel;
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class QuestionListResponse {
        private List<QuestionDto> questions;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class QuestionDto {
        private Long questionId;
        private String questionText;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubmitAnswerRequest {
        private Long userId;
        private Long questionId;
        private String answerText;
    }
}