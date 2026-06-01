package com.example._rdproject.repository;

import com.example._rdproject.entity.UserCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {
    // 유저의 모든 캐릭터 호감도/해금 상태 정보를 페치 조인으로 땡겨오기 (성능 최적화)
    @Query("select uc from UserCharacter uc join fetch uc.character where uc.user.id = :userId")
    List<UserCharacter> findAllByUserIdWithCharacter(@Param("userId") Long userId);
}