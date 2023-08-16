package com.wanted.board.domain.post.api;

import com.wanted.board.domain.post.application.PostService;
import com.wanted.board.domain.post.dto.PostAddRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String createPost(@RequestBody PostAddRequest postAddPutRequest, Authentication authentication) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(postService.addPost(postAddPutRequest, authentication.getName());
        log.info(authentication.getName());
        return "sd";
    }
}
