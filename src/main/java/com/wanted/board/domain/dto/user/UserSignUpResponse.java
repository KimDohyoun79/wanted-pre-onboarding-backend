package com.wanted.board.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserSignUpResponse {
    private Long id;
    private String userEmail;
}
