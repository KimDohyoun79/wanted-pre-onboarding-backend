package com.wanted.board.domain.post;

import com.wanted.board.domain.post.application.PostService;
import com.wanted.board.domain.post.dao.PostRepository;
import com.wanted.board.domain.post.domain.PostEntity;
import com.wanted.board.domain.post.dto.PostAddRequest;
import com.wanted.board.domain.post.dto.PostAddResponse;
import com.wanted.board.domain.user.dao.UserRepository;
import com.wanted.board.domain.user.domain.UserEntity;
import com.wanted.board.domain.user.dto.userRole.UserRole;
import com.wanted.board.global.error.AppException;
import com.wanted.board.global.error.ErrorCode;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostServiceTest {

    PostService postService;

    PostRepository postRepository = Mockito.mock(PostRepository.class);
    UserRepository userRepository = Mockito.mock(UserRepository.class);

    @BeforeEach
    void setUp() {
        postService = new PostService(postRepository, userRepository);
    }

    @Test
    @DisplayName("게시물 작성 성공")
    void addPostSuccess() {

        PostEntity mockPostEntity = mock(PostEntity.class);
        UserEntity mockUserEntity = mock(UserEntity.class);

        String userEmail = "dokim@naver.com";
        PostAddRequest postAddRequest = new PostAddRequest("title", "body");

        when(userRepository.findByEmail(userEmail))
                .thenReturn(Optional.of(mockUserEntity));
        when(postRepository.save(any()))
                .thenReturn(mockPostEntity);

        Assertions.assertDoesNotThrow(() -> postService.addPost(postAddRequest, userEmail));
    }

    @Test
    void getPostList() {
    }


    @Test
    @DisplayName("게시물 조회 성공")
    void getDetaileSuccesst() {

        UserEntity mockUserEntity = mock(UserEntity.class);
        PostEntity postEntity = new PostEntity(1L, "title", "body", mockUserEntity);

        when(postRepository.findById(1L))
                .thenReturn(Optional.of(postEntity));

        assertEquals(1L, postEntity.getId());
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }
    @Test
    @DisplayName("포스트 삭제 성공")
    void deletePostSuccess() {
        PostEntity mockPostEntity = mock(PostEntity.class);
        UserEntity mockUserEntity = mock(UserEntity.class);

        String userEmail = "dokim@naver.com";
        UserEntity userEntity = new UserEntity(1L, "dokim@naver.com", "1q2w3e4r", "dokim", UserRole.ROLE_USER);
        PostEntity postEntity = new PostEntity(1L, "title", "body", userEntity);

        when(postRepository.findById(1L))
                .thenReturn(Optional.of(postEntity));
        when(userRepository.findByEmail(userEmail))
                .thenReturn(Optional.of(mockUserEntity));

        when(mockPostEntity.getUserEntity()).thenReturn(userEntity);

        assertTrue((BooleanSupplier) postService.deletePost(1L, ""));
    }
}