package com.wanted.board.domain.post.dto;

import com.wanted.board.domain.post.domain.PostEntity;
import com.wanted.board.domain.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PostAddRequest {
    private String title;
    private String body;

    public PostEntity toPostEntity(UserEntity userEntity) {
        return PostEntity.builder()
                .title(this.title)
                .body(this.body)
                .userEntity(userEntity)
                .build();
    }
}
