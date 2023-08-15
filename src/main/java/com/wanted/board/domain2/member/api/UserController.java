package com.wanted.board.domain2.member.api;

import com.wanted.board.domain2.member.dto.SignUpRequestDto;
import com.wanted.board.domain2.member.dto.SignUpResponseDto;
import com.wanted.board.domain2.member.dto.LoginRequestDto;
import com.wanted.board.domain2.member.dto.LoginResponseDto;
import com.wanted.board.domain2.member.application.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/test")
    public String test(){
        return "Connection Clear";
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.signUp(signUpRequestDto));
    }


    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.login(loginRequestDto));
    }
}
