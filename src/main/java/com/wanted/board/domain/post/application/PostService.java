package com.wanted.board.domain.post.application;

import com.wanted.board.domain.post.dao.PostRepository;
import com.wanted.board.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;


//    public Object addPost(PostAddRequest postAddPutRequest, String name) {
//
//        // 이메일 확인
//        UserEntity userEntity = userRepository.findByEmail(postAddPutRequest.getEmail())
//                .orElseThrow(() ->  new AppException(ErrorCode.USEREMAIL_NOT_FOUND, ErrorCode.USEREMAIL_NOT_FOUND.getMessage()));
//
//    }
}
