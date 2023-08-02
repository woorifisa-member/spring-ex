package com.woorifisa.board.common.dto.session;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberSession {

	public static final String KEY = "MEMBER";

	private final Long id;
	private final String email;

}
