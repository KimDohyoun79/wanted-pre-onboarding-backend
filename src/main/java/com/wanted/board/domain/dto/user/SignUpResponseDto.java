package com.wanted.board.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignUpResponseDto {
    private Long id;
    private String userEmail;
    private String userName;
}
