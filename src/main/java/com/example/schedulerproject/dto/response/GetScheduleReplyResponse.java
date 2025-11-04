package com.example.schedulerproject.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetScheduleReplyResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<GetReplyResponse> replies;

    public GetScheduleReplyResponse(Long id, String title, String content, String userName, LocalDateTime createdAt, LocalDateTime modifiedAt, List<GetReplyResponse> replies) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.replies = replies;
    }
}