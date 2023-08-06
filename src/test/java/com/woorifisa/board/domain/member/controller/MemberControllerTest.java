package com.woorifisa.board.domain.member.controller;

import static java.nio.charset.StandardCharsets.*;
import static org.hamcrest.Matchers.*;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woorifisa.board.domain.dto.member.request.DummySignupRequest;
import com.woorifisa.board.domain.member.dto.request.SignupRequest;
import com.woorifisa.board.domain.member.dto.response.MemberResponse;
import com.woorifisa.board.domain.member.service.MemberService;

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

		// System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(dummySignupRequest));

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

}