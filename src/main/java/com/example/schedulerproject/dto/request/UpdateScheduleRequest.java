package com.example.schedulerproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * 일정 수정 시 입력 request DTO
 */
@Getter
public class UpdateScheduleRequest {

    @NotBlank(message = "일정 제목 필수 작성")
    @Size(max = 30, message = "30이하의 글자만 입력해주세요.")
    private String title;

    @NotBlank(message = "작성자 필수 작성")
    private String userName;

    @NotBlank(message = "비밀번호 필수 작성")
    private String password;
}
