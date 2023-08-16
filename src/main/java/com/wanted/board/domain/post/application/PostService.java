package com.wanted.board.domain.post.application;

import com.wanted.board.domain.post.dao.PostRepository;
import com.wanted.board.domain.post.domain.PostEntity;
import com.wanted.board.domain.post.dto.*;
import com.wanted.board.domain.user.dao.UserRepository;
import com.wanted.board.domain.user.domain.UserEntity;
import com.wanted.board.domain.user.dto.userRole.UserRole;
import com.wanted.board.global.error.AppException;
import com.wanted.board.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;


    // 게시글 작성
    @Transactional
    public PostAddResponse addPost(PostAddRequest postAddPutRequest, String userEmail) {

        // 이메일 확인
        UserEntity userEntity = userRepository.findByEmail(userEmail)
                .orElseThrow(() ->  new AppException(ErrorCode.USEREMAIL_NOT_FOUND, "회원가입을 먼저 해주세요."));

        // DB 저장
        PostEntity postEntity = postRepository.save(postAddPutRequest.toPostEntity(userEntity));

        log.info("[PostService]-[addPost] check : email:{}, postId:{}", userEntity.getEmail(), postEntity.getId());
        return new PostAddResponse("게시글 등록 완료", postEntity.getId());
    }

    
    // 게시글 조회
    @Transactional
    public List<PostDetailResponse> getPostList(Pageable pageable) {
        Page<PostEntity> posts = postRepository.findAll(pageable);
        List<PostDetailResponse> postDetailResponsesList = posts
                .stream()
                .map(p -> PostDetailResponse.fromPostEntities(p))
                .collect(Collectors.toList());
        return postDetailResponsesList;
    }

    // 게시글 상세조회
    @Transactional
    public PostDetailResponse getDetailePost(Long postId) {
        // 게시글 존재 확인
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.POST_NOT_FOUND, ErrorCode.POST_NOT_FOUND.getMessage());
                });
        return PostDetailResponse.fromPostEntities(postEntity);
    }
    
    // 게시글 수정
    @Transactional
    public PostUpdateResponse updatePost(Long postId, PostUpdateRequest postUpdateRequest, String userEmail) {
        // 게시글 존재 확인
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> {
                    throw new AppException(ErrorCode.POST_NOT_FOUND, ErrorCode.POST_NOT_FOUND.getMessage());
                });

        // 게시글 작성자 존재 확인
        UserEntity userEntity = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.USEREMAIL_NOT_FOUND, ErrorCode.USEREMAIL_NOT_FOUND.getMessage()));

        // 해당 게시글 접근자 확인(작성자, ADMIN)
        if (userEntity.getId() != postEntity.getUserEntity().getId() && userEntity.getRole().equals(UserRole.ROLE_USER)) {
            throw new AppException(ErrorCode.INVALID_PERMISSION, ErrorCode.INVALID_PERMISSION.getMessage());
        }

        // 수정 및
        postEntity.updatePost(postUpdateRequest.getTitle(), postUpdateRequest.getBody());
        postRepository.save(postEntity);

        return new PostUpdateResponse("게시물 수정  완료", postEntity.getId());
    }
}
