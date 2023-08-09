package com.wanted.board.service;

import com.wanted.board.domain.dto.user.UserSignUpRequest;
import com.wanted.board.domain.dto.user.UserSignUpResponse;
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
    public UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest) {

        // 이메일 조건 확인 by '@'
        if (userSignUpRequest.getUserEmail().contains("@")) {
            log.info("이메일 조건 확인 by @ : OK");
        } else
            log.info("이메일 조건 확인 by @ : ON");


        // 비밀번호 8자 이상 확인
        if (userSignUpRequest.getPassword().length() >= 8) {
            log.info("비밀번호 8자 이상 확인 : OK");
        } else
            log.info("비밀번호 8자 이상 확인 : ON");

        // 암호화

        // 회원가입 정보 DB 저장
        UserEntity userEntity = UserSignUpRequest.toUserEntity(userSignUpRequest);
        userRepository.save(userEntity);

        return new UserSignUpResponse(userEntity.getId(), userEntity.getUserEmail());
    }
}
