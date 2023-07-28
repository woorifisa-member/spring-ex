package com.woorifisa.board.common.exception.base;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ExceptionStatus {

    MEMBER_NOT_FOUND(404, "", NOT_FOUND),
    LOGIN_FAIL(401, "", UNAUTHORIZED),
    FORBIDDEN(403, "", HttpStatus.FORBIDDEN),

    POST_NOT_FOUND(404, "", NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

}
