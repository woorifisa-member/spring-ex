package com.woorifisa.board.domain.member.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberResponse {

    private final String email;
    private final String name;

}
