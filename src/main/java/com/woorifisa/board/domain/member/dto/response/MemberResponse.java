package com.woorifisa.board.domain.member.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberResponse {

	private final Long id;
	private final String email;
	private final String name;

}
