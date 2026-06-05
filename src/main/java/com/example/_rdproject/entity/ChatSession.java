package com.example._rdproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatSession {
    @Id
    private String sessionId;
    private Long userId;
    private Long stageId;
    private String characterId;
    private LocalDateTime createdAt;
}
