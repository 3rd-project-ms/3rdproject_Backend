package com.example._rdproject.domain;

public enum AffinityReasonType {
    // [호감도 상승]
    UP_GOOD_CONVERSATION, // 즐거운 대화: 적절한 리액션 및 유창한 대화 성공
    UP_TASK_COMPLETION,   // 과제 완료: 해당 스테이지의 미션 성공

    // [호감도 하락]
    DOWN_GRAMMAR_ERROR,      // 문법 오류: 잦은 문법 실수로 인한 대화 흐름 방해
    DOWN_KOREAN_USAGE,       // 한국어 사용: 영어 학습 목적에 어긋나는 모국어 남용
    DOWN_OFFENSIVE_LANGUAGE  // 부적절한 언어: 정지 수준은 아니지만 캐릭터의 기분을 상하게 하는 워딩
}
