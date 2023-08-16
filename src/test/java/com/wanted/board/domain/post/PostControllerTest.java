package com.wanted.board.domain.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.board.domain.post.api.PostController;
import com.wanted.board.domain.post.application.PostService;
import com.wanted.board.domain.post.domain.PostEntity;
import com.wanted.board.domain.post.dto.*;
import com.wanted.board.domain.user.domain.UserEntity;
import com.wanted.board.domain.user.dto.userRole.UserRole;
import com.wanted.board.global.error.AppException;
import com.wanted.board.global.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PostService postService;

    @Autowired
    ObjectMapper objectMapper;

    @Nested
    @DisplayName("[CreatePost]")
    class CreatePostCase {

        @Test
        @WithMockUser
        @DisplayName("포스트 작성 성공")
        void createPostSuccess() throws Exception {

//        String userEmail = "dokim@naver.com";
//        UserEntity userEntity = new UserEntity(1L, "dokim@naver.com", "1q2w3e4r", "dokim", UserRole.ROLE_USER);
//        PostEntity postEntity =  new PostEntity(1L, "title", "body", userEntity);

            PostAddRequest postAddRequest = new PostAddRequest("tile","body");
            PostAddResponse postAddResponse = new PostAddResponse("d",1L);

            when(postService.addPost(any(), any()))
                    .thenReturn(postAddResponse);

            mockMvc.perform(post("/api/v1/posts/createPost")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(postAddRequest)))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        @WithMockUser
        @DisplayName("포스트 작성 실패 : 비회원 경우")
        void createPostFail1() throws Exception {

            PostAddRequest postAddRequest = new PostAddRequest("tile","body");

            when(postService.addPost(any(), any()))
                    .thenThrow(new AppException(ErrorCode.USEREMAIL_NOT_FOUND, ""));

            mockMvc.perform(post("/api/v1/posts/createPost")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(postAddRequest)))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }
    }


    @Nested
    @DisplayName("[AllPosts]")
    class AllPostsCase {

        @Test
        @WithMockUser
        @DisplayName("포스트 전체 조회 성공")
        void getAllPostsSuccess() throws Exception {

            List<PostDetailResponse> postDetailResponseList = new ArrayList<>();

            when(postService.getPostList(any()))
                    .thenReturn(postDetailResponseList);

            mockMvc.perform(get("/api/v1/posts")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(any())))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }



    @Nested
    @DisplayName("[DetailePost]")
    class DetailePost {

        @Test
        @WithMockUser
        @DisplayName("포스트 1개 조회 성공")
        void getDetailePostSuccess() throws Exception {

            PostDetailResponse postDetailResponse = new PostDetailResponse(1L, "t", "b", "e", "c","l");

            when(postService.getDetailePost(1L))
                    .thenReturn(postDetailResponse);

            mockMvc.perform(get("/api/v1/posts")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(1L)))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        @WithMockUser
        @DisplayName("포스트 1개 조회 실패 : 존재하지 않는 게시물")
        void getDetailePostFail1() throws Exception {

            when(postService.getDetailePost(any()))
                    .thenThrow(new AppException(ErrorCode.POST_NOT_FOUND, ""));

            mockMvc.perform(get("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(1L)))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }
    }



    @Nested
    @DisplayName("[UpdatePost]")
    class UpdatePostCase {

        @Test
        @WithMockUser
        @DisplayName("포스트 수정 성공")
        void  updatePostSuccess() throws Exception {

            PostUpdateRequest postUpdateRequest = new PostUpdateRequest("title", "body");

            PostUpdateResponse postUpdateResponse = new PostUpdateResponse("complete", 1L);

            when(postService.updatePost(any(), any(), any()))
                    .thenReturn(postUpdateResponse);

            mockMvc.perform(put("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(postUpdateRequest)))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        @WithMockUser
        @DisplayName("포스트 수정 실패 : 존재하지 않는 게시글")
        void  updatePostFail1() throws Exception {

            PostUpdateRequest postUpdateRequest = new PostUpdateRequest("title", "body");

            when(postService.updatePost(any(), any(), any()))
                    .thenThrow(new AppException(ErrorCode.POST_NOT_FOUND, ""));

            mockMvc.perform(put("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(postUpdateRequest)))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @WithMockUser
        @DisplayName("포스트 수정 실패 : 작성자가 아닌 경우")
        void  updatePostFail2() throws Exception {

            PostUpdateRequest postUpdateRequest = new PostUpdateRequest("title", "body");

            when(postService.updatePost(any(), any(), any()))
                    .thenThrow(new AppException(ErrorCode.USEREMAIL_NOT_FOUND, ""));

            mockMvc.perform(put("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(postUpdateRequest)))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }
    }


    @Nested
    @DisplayName("[DeletePost]")
    class DeletePostCase {

        @Test
        @WithMockUser
        @DisplayName("포스트 삭제 성공")
        void  deletePostSuccess() throws Exception {

            when(postService.deletePost(any(), any()))
                    .thenReturn(new PostDeleteResponse("게시물 삭제 완료", 1L));

            mockMvc.perform(delete("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        @WithMockUser
        @DisplayName("포스트 삭제 실패 : 존재하지 않는 게시글")
        void  deletePostFail1() throws Exception {

            when(postService.deletePost(any(), any()))
                    .thenThrow(new AppException(ErrorCode.POST_NOT_FOUND, ""));

            mockMvc.perform(delete("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @WithMockUser
        @DisplayName("포스트 삭제 실패 : 작성자가 아닌 경우")
        void  deletePostFail2() throws Exception {

            when(postService.deletePost(any(), any()))
                    .thenThrow(new AppException(ErrorCode.USEREMAIL_NOT_FOUND, ""));

            mockMvc.perform(delete("/api/v1/posts/1")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }
    }

}