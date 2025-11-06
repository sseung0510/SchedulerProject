package com.example.schedulerproject.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 댓글 생성 응답 response DTO
 */
@Getter
public class CreateReplyResponse {
    private final long replyId;
    private final String replyContent;
    private final String userName;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public CreateReplyResponse(long replyId, String replyContent, String userName, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.replyId = replyId;
        this.replyContent = replyContent;
        this.userName = userName;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
