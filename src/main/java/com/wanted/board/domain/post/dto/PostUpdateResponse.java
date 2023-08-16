package com.wanted.board.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class PostUpdateResponse {
    private String message;
    private Long postId;
}
