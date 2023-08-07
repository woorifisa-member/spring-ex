package com.woorifisa.board.domain.member.controller;

import static java.nio.charset.StandardCharsets.*;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woorifisa.board.common.dto.session.MemberSession;
import com.woorifisa.board.domain.dto.member.request.DummySignupRequest;
import com.woorifisa.board.domain.member.dto.request.LoginRequest;
import com.woorifisa.board.domain.member.dto.request.SignupRequest;
import com.woorifisa.board.domain.member.dto.response.MemberResponse;
import com.woorifisa.board.domain.member.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Getter;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

	MockMvc mockMvc;

	@Autowired
	MemberController memberController;

	@MockBean
	MemberService memberService;

	@Autowired
	ObjectMapper om;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).alwaysDo(print()).build();
	}

	@DisplayName("회원가입")
	@Test
	void testSignup() throws Exception {

		Long id = 1L;
		String email = "email@email.com";
		String name = "홍길동";
		String password = "password";
		MemberResponse memberResponse = new MemberResponse(id, email, name);
		DummySignupRequest dummySignupRequest = new DummySignupRequest(email, password, name);
		String requestBody = om.writeValueAsString(dummySignupRequest);

		System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(dummySignupRequest));

		given(memberService.signup(any(SignupRequest.class))).willReturn(memberResponse);

		mockMvc.perform(
				post("/members/signup").characterEncoding(UTF_8).contentType(APPLICATION_JSON).content(requestBody))
			// assertThat
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
			.andExpect(jsonPath("$.data.id", is(id), Long.class))
			.andExpect(jsonPath("$.data.email", is(email)));
		then(memberService).should(times(1)).signup(any(SignupRequest.class));
	}

	@DisplayName("로그인")
	@Test
	void testLogin() throws Exception {

		Long id = 1L;
		String email = "email@email.com";
		String password = "password";
		String name = "홍길동";

		MockHttpSession session = spy(new MockHttpSession());

		// HttpSession mockSession = mock(HttpSession.class);
		// willDoNothing().given(mockSession).setAttribute("hello", "world");

		DummyLoginRequest req = new DummyLoginRequest(email, password);
		MemberResponse memberResponse = new MemberResponse(id, email, name);

		String requestBody = om.writeValueAsString(req);
		given(memberService.login(any(LoginRequest.class))).willReturn(memberResponse);

		mockMvc.perform(post("/members/login")
				.session(session)
				.characterEncoding(UTF_8)
				.contentType(APPLICATION_JSON)
				.content(requestBody))

			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
			.andExpect(jsonPath("$.data.id", is(id), Long.class))
			.andExpect(jsonPath("$.data.email", is(email)))
			.andExpect(jsonPath("$.data.name", is(name)));

		MemberSession attribute = (MemberSession)session.getAttribute(MemberSession.KEY);

		assertAll(
			() -> assertThat(attribute).isNotNull(),
			() -> assertThat(attribute.getEmail()).isEqualTo(email)
		);

		then(memberService).should(times(1)).login(any(LoginRequest.class));
		then(session).should(times(1)).setAttribute(anyString(), any(MemberSession.class));
	}

	@AllArgsConstructor
	@Getter
	static class DummyLoginRequest {
		String email;
		String password;
	}

}
