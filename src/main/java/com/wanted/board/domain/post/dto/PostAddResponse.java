package com.wanted.board.domain.post.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostAddResponse {
    private String message;
    private Long postId;
}
