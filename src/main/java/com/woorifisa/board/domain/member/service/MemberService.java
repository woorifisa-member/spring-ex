package com.woorifisa.board.domain.member.service;

import com.woorifisa.board.domain.member.dto.request.LoginRequest;
import com.woorifisa.board.domain.member.dto.request.SignupRequest;
import com.woorifisa.board.domain.member.dto.response.MemberResponse;

public interface MemberService {

    MemberResponse signup(SignupRequest signupRequest);

    MemberResponse login(LoginRequest loginRequest);

}
