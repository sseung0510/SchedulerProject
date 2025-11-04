package com.example.schedulerproject.dto.request;

import lombok.Getter;

@Getter
public class CreateReplyRequest {
    private String replyContent;
    private String userName;
    private String password;
}
