package com.example._rdproject.entity;

import com.example._rdproject.domain.ChatInputType;
import com.example._rdproject.domain.ChatRoleType;
import com.example._rdproject.domain.PenaltyReasonType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ChatLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String messageId;    // 메시지 고유 식별자
    private Long userId;         // 누가 보냈는지
    private String characterId;  // 어떤 캐릭터와 대화 중인지
    private String sessionId;    // 채팅방 구분자 (질문하신 그 세션 ID)
    private String scenarioId;   // 현재 진행 중인 시나리오
    private Integer turnCount;   // 대화 순서

    @Enumerated(EnumType.STRING)
    private ChatRoleType role;   // USER 혹은 AI

    @Enumerated(EnumType.STRING)
    private ChatInputType inputType; // VOICE 혹은 TEXT

    @Column(columnDefinition = "TEXT")
    private String textContent;  // 대화 내용
    private String audioUrl;     // 음성 데이터 링크

    @Enumerated(EnumType.STRING)
    private PenaltyReasonType penaltyReason; // 페널티 발생 시 이유

    private Integer affinityDelta; // 호감도 변화량
    private LocalDateTime createdAt;
}
