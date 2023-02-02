package com.simplilearn.sportyshoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.sportyshoe.dto.ResponseDto;
import com.simplilearn.sportyshoe.dto.user.SignInDto;
import com.simplilearn.sportyshoe.dto.user.SignInResponseDto;
import com.simplilearn.sportyshoe.dto.user.SignupDto;
import com.simplilearn.sportyshoe.service.UserService;

@RequestMapping("user")
@RestController
public class UserController {
	@Autowired
    UserService userService;

    // two apis

    // signup

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
        return userService.signUp(signupDto);
    }


    // signin

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }


}


