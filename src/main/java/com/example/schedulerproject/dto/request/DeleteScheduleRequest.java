package com.example.schedulerproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteScheduleRequest {
    private Long id;

    @NotBlank(message = "비밀번호 필수 작성")
    private String password;
}
