package com.wanted.board.service;

import com.wanted.board.domain.dto.user.SignUpRequestDto;
import com.wanted.board.domain.dto.user.SignUpResponseDto;
import com.wanted.board.domain.entity.UserEntity;
import com.wanted.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    // 회원 가입
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {

        // 암호화


        // 회원가입 정보 DB 저장
        UserEntity userEntity = SignUpRequestDto.toUserEntity(signUpRequestDto);
        userRepository.save(userEntity);

        return new SignUpResponseDto(userEntity.getId(), userEntity.getEmail(), userEntity.getUserName());
    }
}
