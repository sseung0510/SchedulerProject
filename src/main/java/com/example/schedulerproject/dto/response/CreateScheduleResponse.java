package com.example.schedulerproject.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 일정 생성 응답 response DTO
 */
@Getter
public class CreateScheduleResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String userName;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public CreateScheduleResponse(Long id, String title, String content, String userName, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
