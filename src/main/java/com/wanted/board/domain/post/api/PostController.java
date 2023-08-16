package com.wanted.board.domain.post.api;

import com.wanted.board.domain.post.application.PostService;
import com.wanted.board.domain.post.dto.PostAddRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    // 게시물 등록
    @PostMapping("/createPost")
    public ResponseEntity<Object> createPost(@RequestBody PostAddRequest postAddPutRequest, Authentication authentication) {
        log.info("[PostController]-[createPost] check : email:{}", authentication.getName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.addPost(postAddPutRequest, authentication.getName()));
    }
}
