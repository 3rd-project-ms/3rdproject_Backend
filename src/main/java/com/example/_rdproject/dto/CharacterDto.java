package com.example._rdproject.dto;

import com.example._rdproject.entity.User.CefrLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

public class CharacterDto {

    // 1. 메인 화면 응답 전체 DTO
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MainStatusResponse {
        private Long userId;
        private String nickname;
        private CefrLevel currentLevel;
        private Integer continuousDays;
        private List<CharacterStatusResponse> characters;
    }

    // 1-1. 메인 화면 속 개별 캐릭터 정보 DTO
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CharacterStatusResponse {
        private Long characterId;
        private String name;
        private String description;
        private String imageUrl;
        private Integer affinityScore;
        private boolean isUnlocked;
    }

    // 2. 특정 캐릭터의 스테이지 목록 응답 DTO
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StageListResponse {
        private Long characterId;
        private String characterName;
        private List<StageResponse> stages;
    }

    // 2-1. 개별 스테이지 세부 정보 DTO
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StageResponse {
        private Long stageId;
        private Integer stageNumber;
        private String title;
        private String situationDescription;
    }
}