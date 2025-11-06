package com.example.schedulerproject.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponse {
    private final Long id;
    private final String title;
    private final String userName;
    private final LocalDateTime modifiedAt;

    public UpdateScheduleResponse(Long id, String title, String userName, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.userName = userName;
        this.modifiedAt = modifiedAt;
    }
}
