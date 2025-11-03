package com.example.schedulerproject.dto.request;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    private String title;
    private String content;
    private String userName;
    private String userPwd;
}
