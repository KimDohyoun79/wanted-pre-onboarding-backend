package com.wanted.board.domain2.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignUpResponseDto {
    private Long id;
    private String email;
    private String userName;
}
