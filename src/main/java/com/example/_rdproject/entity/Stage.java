package com.example._rdproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stages")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", nullable = false)
    private Character character;

    @Column(nullable = false)
    private Integer stageNumber; // 1단계, 2단계...

    @Column(nullable = false)
    private String title; // 상황 지문 및 미션 타이틀 (예: "카페에서 주문하기")

    private String situationDescription; // 상황 묘사 지문 원문
}