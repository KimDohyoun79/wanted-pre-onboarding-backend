package com.wanted.board.domain.post.application;

import com.wanted.board.domain.post.dao.PostRepository;
import com.wanted.board.domain.post.domain.PostEntity;
import com.wanted.board.domain.post.dto.PostAddRequest;
import com.wanted.board.domain.user.dao.UserRepository;
import com.wanted.board.domain.user.domain.UserEntity;
import com.wanted.board.global.error.AppException;
import com.wanted.board.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;


    public Object addPost(PostAddRequest postAddPutRequest, String email) {

        // 이메일 확인
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() ->  new AppException(ErrorCode.USEREMAIL_NOT_FOUND, "회원가입을 먼저 해주세요."));

        // DB 저장
        PostEntity postEntity = postRepository.save(postAddPutRequest.toPostEntity(userEntity));

        log.info("[PostService]-[addPost] check : email:{}, postId:{}", userEntity.getEmail(), postEntity.getId());
        return "good";
    }
}
