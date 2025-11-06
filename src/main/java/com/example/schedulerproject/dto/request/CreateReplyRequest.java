package com.example.schedulerproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateReplyRequest {

    @NotBlank(message = "댓글 내용 필수 작성")
    @Size(max = 100, message = "100이하의 글자만 입력해주세요.")
    private String replyContent;

    @NotBlank(message = "작성자 필수 작성")
    private String userName;

    @NotBlank(message = "비밀번호 필수 작성")
    private String password;
}
