package com.example._rdproject.repository;

import com.example._rdproject.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StageRepository extends JpaRepository<Stage, Long> {
    // 특정 캐릭터 ID에 속한 스테이지 목록 정렬 조회
    List<Stage> findByCharacterIdOrderByStageNumberAsc(Long characterId);
}