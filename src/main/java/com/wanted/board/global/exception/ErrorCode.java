package com.wanted.board.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {


    USEREMAIL_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 이메일이 존재하지 않습니다. 이메일을 확인해 주세요."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "패스워드가 잘못되었습니다. 패스워드를 확인해 주세요."),



    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "UserName이 중복됩니다."),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND,"Not founded"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰입니다."),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "사용자가 권한이 없습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 포스트가 없습니다."),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DB에러"),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 댓글이 없습니다."),
    ALREADY_PUSH_LIKE(HttpStatus.CONFLICT, "이미 <좋아요>를 눌렀습니다."),
    ROLE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 <Role>이 없습니다.");

    private HttpStatus httpStatus;
    private String message;
}
