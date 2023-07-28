package com.woorifisa.board.domain.member.controller;

import com.woorifisa.board.common.dto.response.CommonResponse;
import com.woorifisa.board.domain.member.dto.request.LoginRequest;
import com.woorifisa.board.domain.member.dto.request.SignupRequest;
import com.woorifisa.board.domain.member.dto.response.MemberResponse;
import com.woorifisa.board.domain.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<MemberResponse>> signup(@RequestBody @Valid SignupRequest signupRequest) {

        MemberResponse signup = memberService.signup(signupRequest);

        return CommonResponse.success(HttpStatus.OK, 200, signup);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<MemberResponse>> login(@RequestBody LoginRequest loginRequest) {

        MemberResponse login = memberService.login(loginRequest);

        return CommonResponse.success(HttpStatus.OK, 200, login);
    }

    public void logout() {

    }

}
