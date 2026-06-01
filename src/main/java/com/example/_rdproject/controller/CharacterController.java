package com.example._rdproject.controller;

import com.example._rdproject.dto.CharacterDto;
import com.example._rdproject.dto.common.CommonResponse;
import com.example._rdproject.service.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Lobby & Character", description = "로비 메인 및 캐릭터/스테이지 관련 API")
@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @Operation(
            summary = "메인 화면 캐릭터 목록 및 유저 상태 조회",
            description = "홈 화면 진입 시 유저의 기본 상태 데이터와 캐릭터별 호감도/해금 상태 정보를 종합 반환합니다."
    )
    @GetMapping("/status")
    public ResponseEntity<CommonResponse<CharacterDto.MainStatusResponse>> getMainStatus(
            @RequestParam(name = "userId") Long userId
    ) {
        CharacterDto.MainStatusResponse data = characterService.getMainStatus(userId);
        // 첫 번째: 메시지 문자열, 두 번째: 실제 데이터 DTO
        return ResponseEntity.ok(CommonResponse.success("메인 로비 데이터 조회가 완료되었습니다.", data));
    }

    @Operation(
            summary = "특정 캐릭터의 스테이지 리스트 조회",
            description = "선택한 캐릭터에 종속된 챕터/스테이지들의 세부 미션 리스트를 조회합니다."
    )
    @GetMapping("/{character_id}/stages")
    public ResponseEntity<CommonResponse<CharacterDto.StageListResponse>> getCharacterStages(
            @PathVariable(name = "character_id") Long characterId
    ) {
        CharacterDto.StageListResponse data = characterService.getCharacterStages(characterId);
        // 첫 번째: 메시지 문자열, 두 번째: 실제 데이터 DTO
        return ResponseEntity.ok(CommonResponse.success("캐릭터별 스테이지 목록 조회가 완료되었습니다.", data));
    }
}