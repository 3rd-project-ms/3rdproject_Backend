package com.example._rdproject.service;

import com.example._rdproject.dto.CharacterDto;
import com.example._rdproject.entity.Character;
import com.example._rdproject.entity.Stage;
import com.example._rdproject.entity.User;
import com.example._rdproject.entity.UserCharacter;
import com.example._rdproject.repository.CharacterRepository;
import com.example._rdproject.repository.StageRepository;
import com.example._rdproject.repository.UserCharacterRepository;
import com.example._rdproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CharacterService {

    private final UserRepository userRepository;
    private final UserCharacterRepository userCharacterRepository;
    private final StageRepository stageRepository;
    private final CharacterRepository characterRepository;

    /**
     * 메인 화면 캐릭터 목록 및 유저 상태 종합 조회
     */
    public CharacterDto.MainStatusResponse getMainStatus(Long userId) {
        // 1. 유저 정보 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다. ID: " + userId));

        // 2. 유저의 캐릭터 매핑 데이터 조회
        List<UserCharacter> userCharacters = userCharacterRepository.findAllByUserIdWithCharacter(userId);

        // 3. 만약 신규 가입 유저라 매핑 데이터가 없다면 기본 매핑 데이터 동적 초기화 및 생성 (방어 코드)
        if (userCharacters.isEmpty()) {
            List<Character> allCharacters = characterRepository.findAll();
            userCharacters = allCharacters.stream().map(character -> {
                UserCharacter uc = UserCharacter.builder()
                        .user(user)
                        .character(character)
                        .affinityScore(0)
                        .isUnlocked(character.isInitialUnlocked()) // 마스터 테이블 세팅 기본값 따름
                        .build();
                return userCharacterRepository.save(uc);
            }).collect(Collectors.toList());
        }

        // 4. DTO 변환
        List<CharacterDto.CharacterStatusResponse> characterStatuses = userCharacters.stream()
                .map(uc -> new CharacterDto.CharacterStatusResponse(
                        uc.getCharacter().getId(),
                        uc.getCharacter().getName(),
                        uc.getCharacter().getDescription(),
                        uc.getCharacter().getImageUrl(),
                        uc.getAffinityScore(),
                        uc.isUnlocked()
                )).collect(Collectors.toList());

        return new CharacterDto.MainStatusResponse(
                user.getId(),
                user.getNickname(),
                user.getCurrentLevel(),
                user.getContinuousDays(),
                characterStatuses
        );
    }

    /**
     * 특정 캐릭터의 스테이지 목록 조회
     */
    public CharacterDto.StageListResponse getCharacterStages(Long characterId) {
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 캐릭터입니다. ID: " + characterId));

        List<Stage> stages = stageRepository.findByCharacterIdOrderByStageNumberAsc(characterId);

        List<CharacterDto.StageResponse> stageResponses = stages.stream()
                .map(stage -> new CharacterDto.StageResponse(
                        stage.getId(),
                        stage.getStageNumber(),
                        stage.getTitle(),
                        stage.getSituationDescription()
                )).collect(Collectors.toList());

        return new CharacterDto.StageListResponse(character.getId(), character.getName(), stageResponses);
    }
}