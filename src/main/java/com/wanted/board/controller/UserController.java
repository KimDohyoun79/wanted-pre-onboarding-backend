package com.wanted.board.controller;

import com.wanted.board.domain.dto.user.SignUpRequestDto;
import com.wanted.board.domain.dto.user.SignUpResponseDto;
import com.wanted.board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.signUp(signUpRequestDto));
    }

//    @PostMapping("/signup")
//    public SignUpResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto){
//        return userService.signUp(signUpRequestDto);
//    }
}
