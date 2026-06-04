package com.example._rdproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AnswerHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(columnDefinition = "TEXT")
    private String answerText; // STT 결과 혹은 텍스트 답변

    @Enumerated(EnumType.STRING)
    private AnswerType answerType; // VOICE 혹은 TEXT (Enum으로 정의 필요)

    private String aiFeedback; // 추후 AI가 분석한 내용 저장용

    public enum AnswerType {
        VOICE, TEXT
    }

    @Builder
    public AnswerHistory(User user, Question question, String answerText, AnswerType answerType) {
        this.user = user;
        this.question = question;
        this.answerText = answerText;
        this.answerType = answerType;
    }
}
