package com.example.schedulerproject.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 댓글 조회 response DTO
 */
@Getter
public class GetReplyResponse {
    private final long replyId;
    private final String replyContent;
    private final String replyUserName;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    public GetReplyResponse(long replyId, String replyContent, String replyUserName, LocalDateTime createAt, LocalDateTime updateAt) {
        this.replyId = replyId;
        this.replyContent = replyContent;
        this.replyUserName = replyUserName;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
