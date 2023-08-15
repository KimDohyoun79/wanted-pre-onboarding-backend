package com.wanted.board.service;

import com.wanted.board.domain.dto.user.SignUpRequestDto;
import com.wanted.board.domain.dto.user.SignUpResponseDto;
import com.wanted.board.domain.entity.UserEntity;
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
}
