package com.wanted.board.domain.user.dto;

import com.wanted.board.domain.user.dto.userRole.UserRole;
import com.wanted.board.domain.user.domain.UserEntity;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SignUpRequestDto {

    @Email(message = "이메일 형식으로 입력해주세요.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @Size(min = 8, message = "8자리 이상의 비밀번호를 입력해주세요.")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Nullable
    private String userName;

    public static UserEntity toUserEntity(SignUpRequestDto signUpRequestDto, String pass) {
        return UserEntity.builder()
                .email(signUpRequestDto.email)
                .password(pass)
                .userName(signUpRequestDto.userName == null ? "anonymous user" : signUpRequestDto.userName)
                .role(UserRole.ROLE_USER)
                .build();
    }
}
