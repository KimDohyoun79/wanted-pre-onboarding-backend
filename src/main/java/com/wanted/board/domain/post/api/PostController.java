package com.wanted.board.domain.post.api;

import com.wanted.board.domain.post.application.PostService;
import com.wanted.board.domain.post.dto.PostAddRequest;
import com.wanted.board.domain.post.dto.PostDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 게시글 전제 조회
    @GetMapping()
    public ResponseEntity<List<PostDetailResponse>> getAllPosts(@PageableDefault(page = 0, size = 20,
            sort = "registeredAt", direction = Sort.Direction.DESC) Pageable pageable) {

        List<PostDetailResponse> posts = postService.getPostList(pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .body(posts);
    }

}
