package com.wanted.board.domain.post.dto;

import com.wanted.board.domain.post.domain.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Builder
@AllArgsConstructor
@Getter
public class PostDetailResponse {
    private Long id;
    private String title;
    private String body;
    private String userEmail;
    private String createdAt;
    private String lastModifiedAt;

    public static PostDetailResponse fromPostEntities(PostEntity postEntity) {

        String createdAtTmp;
        String lastModifiedAtTmp;

        if(postEntity.getRegisteredAt() == null)
            createdAtTmp ="";
        else
            createdAtTmp = postEntity.getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if(postEntity.getLastModifiedAt() == null)
            lastModifiedAtTmp ="";
        else
            lastModifiedAtTmp = postEntity.getLastModifiedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return PostDetailResponse.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .body(postEntity.getBody())
                .userEmail(postEntity.getUserEntity().getEmail())
                .createdAt(createdAtTmp)
                .lastModifiedAt(lastModifiedAtTmp)
                .build();
    }
}
