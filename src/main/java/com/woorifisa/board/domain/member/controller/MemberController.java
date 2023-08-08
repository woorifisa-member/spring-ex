package com.woorifisa.board.domain.member.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.woorifisa.board.common.dto.response.CommonResponse;
import com.woorifisa.board.common.dto.session.MemberSession;
import com.woorifisa.board.domain.member.dto.request.LoginRequest;
import com.woorifisa.board.domain.member.dto.request.SignupRequest;
import com.woorifisa.board.domain.member.dto.response.MemberResponse;
import com.woorifisa.board.domain.member.exception.UnauthorizedException;
import com.woorifisa.board.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	public ResponseEntity<CommonResponse<MemberResponse>> login(
		@RequestBody LoginRequest loginRequest,
		HttpSession session) {

		MemberResponse login = memberService.login(loginRequest);
		session.setAttribute(MemberSession.KEY, new MemberSession(login.getId(), login.getEmail()));

		return CommonResponse.success(HttpStatus.OK, 200, login);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@GetMapping("/logout")
	public void logout(HttpSession session) {

		Object attribute = session.getAttribute(MemberSession.KEY);
		log.info("{}", attribute);

		if (attribute != null) {
			session.removeAttribute(MemberSession.KEY);
			session.invalidate();
			return;
		}

		throw new UnauthorizedException();
	}

	@GetMapping("/init")
	public void init() {
		memberService.init();
	}

}
