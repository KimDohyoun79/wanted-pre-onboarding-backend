package com.wanted.board.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    USEREMAIL_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 이메일이 존재하지 않습니다. 이메일을 확인해 주세요."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "패스워드가 잘못되었습니다. 패스워드를 확인해 주세요."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시물이 없습니다.");
    private HttpStatus httpStatus;
    private String message;
}
