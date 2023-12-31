package com.wanted.board.domain.user.application;

import com.wanted.board.domain.user.dto.LoginResponseDto;
import com.wanted.board.domain.user.dto.SignUpRequestDto;
import com.wanted.board.domain.user.dto.SignUpResponseDto;
import com.wanted.board.domain.user.domain.UserEntity;
import com.wanted.board.domain.user.dao.UserRepository;
import com.wanted.board.global.config.jwt.JwtUtil;
import com.wanted.board.domain.user.dto.LoginRequestDto;
import com.wanted.board.global.error.AppException;
import com.wanted.board.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secreteKey;

    private Long expireTimesMs = 1000 * 60 * 60L;

    // 회원가입
    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {

        // 회원가입 비밀번호 암화 & DB 저장
        UserEntity userEntity = userRepository.save(SignUpRequestDto.toUserEntity(signUpRequestDto, passwordEncoder.encode(signUpRequestDto.getPassword())));

        return new SignUpResponseDto(userEntity.getId(), userEntity.getEmail(), userEntity.getUserName());
    }

    // 로그인
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        // 이메일 확인
        UserEntity userEntity = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() ->  new AppException(ErrorCode.USEREMAIL_NOT_FOUND, ErrorCode.USEREMAIL_NOT_FOUND.getMessage()));

        // password 유효성 검사
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), userEntity.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, ErrorCode.INVALID_PASSWORD.getMessage());
        }

        // make token
        return new LoginResponseDto(JwtUtil.creatToken(userEntity.getEmail(), secreteKey, expireTimesMs));
    }
}
