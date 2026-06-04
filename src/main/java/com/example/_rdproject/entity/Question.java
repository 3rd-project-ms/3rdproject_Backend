package com.example._rdproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText; // 질문 내용

    private String difficultyLevel; // 예: "A1", "B2" 등 변별 목표 레벨

    private String category; // 예: "자기소개", "의견 말하기" 등
}
