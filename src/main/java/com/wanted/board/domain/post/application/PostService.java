package com.wanted.board.domain.post.application;

import com.wanted.board.domain.post.dao.PostRepository;
import com.wanted.board.domain.post.domain.PostEntity;
import com.wanted.board.domain.post.dto.PostAddRequest;
import com.wanted.board.domain.post.dto.PostDetailResponse;
import com.wanted.board.domain.user.dao.UserRepository;
import com.wanted.board.domain.user.domain.UserEntity;
import com.wanted.board.global.error.AppException;
import com.wanted.board.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;


    // 게시글 작성
    public String addPost(PostAddRequest postAddPutRequest, String email) {

        // 이메일 확인
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() ->  new AppException(ErrorCode.USEREMAIL_NOT_FOUND, "회원가입을 먼저 해주세요."));

        // DB 저장
        PostEntity postEntity = postRepository.save(postAddPutRequest.toPostEntity(userEntity));

        log.info("[PostService]-[addPost] check : email:{}, postId:{}", userEntity.getEmail(), postEntity.getId());
        return "Add Post Complete";
    }

    // 게시글 조회
    public List<PostDetailResponse> getPostList(Pageable pageable) {
        Page<PostEntity> posts = postRepository.findAll(pageable);
        List<PostDetailResponse> postDetailResponsesList = posts
                .stream()
                .map(p -> PostDetailResponse.fromPostEntities(p))
                .collect(Collectors.toList());
        return postDetailResponsesList;
    }
}
