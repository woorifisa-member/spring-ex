package com.woorifisa.board.common.dto.response;

import com.woorifisa.board.common.exception.base.ExceptionStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponse {

    private final int code;
    private final String message;

    public static ErrorResponse fail(ExceptionStatus status) {
        return new ErrorResponse(status.getCode(), status.getMessage());
    }

}
