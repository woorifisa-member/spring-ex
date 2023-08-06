package com.woorifisa.board.domain.dto.member.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DummySignupRequest {

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String name;

}
