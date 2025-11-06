package com.example.schedulerproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 일정 삭제 시 비밀번호 입력 request DTO
 */
@Getter
public class DeleteScheduleRequest {

    @NotBlank(message = "비밀번호 필수 작성")
    private String password;


}
