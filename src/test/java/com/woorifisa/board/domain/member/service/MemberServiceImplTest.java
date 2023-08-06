package com.woorifisa.board.domain.member.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.woorifisa.board.common.security.PasswordEncoder;
import com.woorifisa.board.domain.dto.mock.MockSignupRequest;
import com.woorifisa.board.domain.member.dto.request.SignupRequest;
import com.woorifisa.board.domain.member.dto.response.MemberResponse;
import com.woorifisa.board.domain.member.entity.Member;
import com.woorifisa.board.domain.member.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

	PasswordEncoder passwordEncoder;
	MemberRepository memberRepository;

	MemberService memberService;

	@BeforeEach
	void setUp() {
		System.out.println("setup");
		passwordEncoder = mock(PasswordEncoder.class);
		memberRepository = mock(MemberRepository.class);

		memberService = new MemberServiceImpl(memberRepository, passwordEncoder);
	}

	@DisplayName("회원가입")
	@Test
	void signup() {
		String email = "email@email.com";
		String password = "password";
		String name = "홍길동";
		String encryptedPassword = "encrypted";

		SignupRequest signupRequest = MockSignupRequest.getDummy(email, password, name);

		Member member = Member.builder()
			.email(signupRequest.getEmail())
			.password(encryptedPassword)
			.name(signupRequest.getName())
			.build();

		Member spyMember = spy(member);
		Long memberId = 1L;

		given(spyMember.getId()).willReturn(memberId);
		given(passwordEncoder.encode(password)).willReturn(encryptedPassword);
		given(memberRepository.save(any(Member.class))).willReturn(spyMember);

		MemberResponse resp = memberService.signup(signupRequest);

		assertThat(resp.getId()).isEqualTo(memberId);
		assertThat(resp.getEmail()).isEqualTo(email);
		assertThat(resp.getName()).isEqualTo(name);

		then(passwordEncoder).should(times(1)).encode(password);
		then(memberRepository).should(times(1)).save(any(Member.class));
	}

	@DisplayName("로그인")
	@Test
	void login() {
	}
}