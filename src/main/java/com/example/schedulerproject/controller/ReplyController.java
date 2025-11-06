package com.example.schedulerproject.controller;

import com.example.schedulerproject.dto.request.CreateReplyRequest;
import com.example.schedulerproject.dto.response.CreateReplyResponse;
import com.example.schedulerproject.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    // lv5 - 댓글 생성
    @PostMapping("/scheduler/{scheduleId}/reply")
    public ResponseEntity<CreateReplyResponse> createReply(
            @PathVariable Long scheduleId,
            @Valid @RequestBody CreateReplyRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(replyService.saveReply(scheduleId, request));
    }
}
