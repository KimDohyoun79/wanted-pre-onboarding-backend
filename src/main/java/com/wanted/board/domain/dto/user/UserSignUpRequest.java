package com.wanted.board.domain.dto.user;

import com.wanted.board.domain.entity.UserEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserSignUpRequest {

    private String userEmail;
    private String password;

    public static UserEntity toUserEntity(UserSignUpRequest userSignUpRequest) {
        return UserEntity.builder()
                .userEmail(userSignUpRequest.getUserEmail())
                .password(userSignUpRequest.getPassword())
                .build();
    }
}
