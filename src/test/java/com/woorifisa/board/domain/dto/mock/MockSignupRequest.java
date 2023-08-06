package com.woorifisa.board.domain.dto.mock;

import org.springframework.test.util.ReflectionTestUtils;

import com.woorifisa.board.domain.member.dto.request.SignupRequest;

public class MockSignupRequest extends SignupRequest {

	public static SignupRequest getDummy(String email, String password, String name) {
		SignupRequest signupRequest = new SignupRequest();

		ReflectionTestUtils.setField(signupRequest, "email", email);
		ReflectionTestUtils.setField(signupRequest, "password", password);
		ReflectionTestUtils.setField(signupRequest, "name", name);

		return signupRequest;
	}

}
