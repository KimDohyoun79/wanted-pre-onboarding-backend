package com.wanted.board.domain.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.board.domain.user.application.UserService;
import com.wanted.board.domain.user.dto.LoginRequestDto;
import com.wanted.board.domain.user.dto.LoginResponseDto;
import com.wanted.board.domain.user.dto.SignUpRequestDto;
import com.wanted.board.domain.user.dto.SignUpResponseDto;
import com.wanted.board.global.error.AppException;
import com.wanted.board.global.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Nested
    @DisplayName("[SignUp]")
    class SignUpCase {

        @Test
        @WithMockUser
        @DisplayName("회원가입 성공")
        void signUpSuccess() throws Exception {

            SignUpRequestDto userJoinRequest = new SignUpRequestDto("dokim@naver.com", "1q2w3e4r", "dokim");

            when(userService.signUp(any()))
                    .thenReturn(new SignUpResponseDto(1L, "dokim@naver.com", "dokim"));

            mockMvc.perform(post("/api/v1/users/signup")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                    .andExpect(jsonPath("$.id").value(1L))
                    .andExpect(jsonPath("$.email").value("dokim@naver.com"))
                    .andExpect(jsonPath("$.userName").value("dokim"))
                    .andExpect(status().isOk())
                    .andDo(print());

            verify(userService).signUp(any());
        }

        @Test
        @WithMockUser
        @DisplayName("회원가입 실패 : 이메일 형식이 아닌 경우")
        void signUpFail1() throws Exception {

            SignUpRequestDto userJoinRequest = new SignUpRequestDto("dokimnaver.com", "1q2w3e4r", "dokim");

            when(userService.signUp(any()))
                    .thenThrow(new AppException(ErrorCode.USEREMAIL_NOT_FOUND, ""));

            mockMvc.perform(post("/api/v1/users/signup")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }

        @Test
        @WithMockUser
        @DisplayName("회원가입 실패 : 비밀번호 8글자 이하")
        void signUpFail2() throws Exception {

            SignUpRequestDto userJoinRequest = new SignUpRequestDto("dokim@naver.com", "1q2w3e", "dokim");

            when(userService.signUp(any()))
                    .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, ""));

            mockMvc.perform(post("/api/v1/users/signup")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("[Login]")
    class LoginCase {

        @Test
        @WithMockUser
        @DisplayName("로그인 성공")
        void loginSuccess() throws Exception {

            LoginRequestDto loginRequestDto = new LoginRequestDto("dokim@naver.com", "1q2w3e4r");

            when(userService.login(any()))
                    .thenReturn(new LoginResponseDto("new token"));

            mockMvc.perform(post("/api/v1/users/login")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(loginRequestDto)))
                    .andExpect(jsonPath("$.jwt").value("new token"))
                    .andExpect(status().isOk())
                    .andDo(print());

            verify(userService).login(any());
        }

        @Test
        @WithMockUser
        @DisplayName("로그인 실패 : 이메일 형식이 아닌 경우")
        void loginFail1() throws Exception {

            LoginRequestDto loginRequestDto = new LoginRequestDto("dokimnaver.com", "1q2w3e4r");

            when(userService.signUp(any()))
                    .thenThrow(new AppException(ErrorCode.USEREMAIL_NOT_FOUND, ""));

            mockMvc.perform(post("/api/v1/users/signup")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(loginRequestDto)))
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }

        @Test
        @WithMockUser
        @DisplayName("로그인 실패 : 이메일이 없는 경우")
        void loginFail2() throws Exception {

            LoginRequestDto loginRequestDto = new LoginRequestDto("dokim@naver.com", "1q2w3e4r");

            when(userService.login(any()))
                    .thenThrow(new AppException(ErrorCode.USEREMAIL_NOT_FOUND, ""));

            mockMvc.perform(post("/api/v1/users/login")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(loginRequestDto)))
                    .andExpect(status().isNotFound())
                    .andDo(print());
        }

        @Test
        @WithMockUser
        @DisplayName("로그인 실패 : 비밀번호 8글자 이하")
        void loginFail3() throws Exception {

            LoginRequestDto loginRequestDto = new LoginRequestDto("dokim@naver.com", "1q2w3e");

            when(userService.login(any()))
                    .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, ""));

            mockMvc.perform(post("/api/v1/users/login")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(loginRequestDto)))
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }

        @Test
        @WithMockUser
        @DisplayName("로그인 실패 : 비밀번호 불일치")
        void loginFail4() throws Exception {

            LoginRequestDto loginRequestDto = new LoginRequestDto("dokim@naver.com", "1q2w3e5t");

            when(userService.login(any()))
                    .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, ""));

            mockMvc.perform(post("/api/v1/users/login")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(loginRequestDto)))
                    .andExpect(status().isUnauthorized())
                    .andDo(print());
        }
    }

}