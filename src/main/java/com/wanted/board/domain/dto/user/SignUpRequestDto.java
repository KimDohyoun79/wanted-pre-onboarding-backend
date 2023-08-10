package com.wanted.board.domain.dto.user;

import com.wanted.board.domain.entity.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;


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

    public static UserEntity toUserEntity(SignUpRequestDto signUpRequestDto) {
        return UserEntity.builder()
                .Email(signUpRequestDto.email)
                .password(signUpRequestDto.password)
                .userName(signUpRequestDto.userName)
                .build();
    }
}
