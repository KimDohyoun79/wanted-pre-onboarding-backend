package com.wanted.board.domain2.member.application;

import com.wanted.board.domain2.member.dto.LoginResponseDto;
import com.wanted.board.domain2.member.dto.SignUpRequestDto;
import com.wanted.board.domain2.member.dto.SignUpResponseDto;
import com.wanted.board.domain2.member.domain.UserEntity;
import com.wanted.board.domain2.member.dao.UserRepository;
import com.wanted.board.global.config.jwt.JwtUtil;
import com.wanted.board.domain2.member.dto.LoginRequestDto;
import com.wanted.board.global.error.AppException;
import com.wanted.board.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        return new LoginResponseDto(JwtUtil.creatToken(userEntity.getUserName(), secreteKey, expireTimesMs));
    }
}
