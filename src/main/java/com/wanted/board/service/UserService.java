package com.wanted.board.service;

import com.wanted.board.domain.dto.user.SignUpRequestDto;
import com.wanted.board.domain.dto.user.SignUpResponseDto;
import com.wanted.board.domain.dto.user.LoginRequestDto;
import com.wanted.board.domain.dto.user.LoginResponseDto;
import com.wanted.board.domain.entity.UserEntity;
import com.wanted.board.exception.AppException;
import com.wanted.board.exception.ErrorCode;
import com.wanted.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    // 회원가입
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {

        // 회원가입 비밀번호 암화 & DB 저장
        UserEntity userEntity = userRepository.save(SignUpRequestDto.toUserEntity(signUpRequestDto, passwordEncoder.encode(signUpRequestDto.getPassword())));

        return new SignUpResponseDto(userEntity.getId(), userEntity.getEmail(), userEntity.getUserName());
    }

    // 로그인
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        // 이메일 확인
        UserEntity userEntity = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() ->  new AppException(ErrorCode.USEREMAIL_NOT_FOUND, ErrorCode.USEREMAIL_NOT_FOUND.getMessage()));

        // password 유효성 검사
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), userEntity.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, ErrorCode.INVALID_PASSWORD.getMessage());
        }

        // make token

        return new LoginResponseDto("token");

    }
}
