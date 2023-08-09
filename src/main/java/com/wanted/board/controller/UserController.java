package com.wanted.board.controller;

import com.wanted.board.domain.dto.user.UserSignUpRequest;
import com.wanted.board.domain.dto.user.UserSignUpResponse;
import com.wanted.board.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/signUp")
    public UserSignUpResponse signUp(@RequestBody UserSignUpRequest userSignUpRequest){
        return userService.signUp(userSignUpRequest);
    }

}
