package com.example._rdproject.controller;

import com.example._rdproject.dto.ChatSessionDto;
import com.example._rdproject.dto.common.CommonResponse;
import com.example._rdproject.service.ChatSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatSessionService chatSessionService;

    @PostMapping("/sessions")
    public ResponseEntity<CommonResponse<ChatSessionDto.CreateResponse>> createSession(
            @RequestBody ChatSessionDto.CreateRequest request) {
        ChatSessionDto.CreateResponse response = chatSessionService.createSession(request);
        return ResponseEntity.ok(CommonResponse.success("대화 세션이 생성되었습니다.", response));
    }
}
