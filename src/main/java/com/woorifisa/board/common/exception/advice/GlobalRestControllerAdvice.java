package com.woorifisa.board.common.exception.advice;

import com.woorifisa.board.common.dto.response.ErrorResponse;
import com.woorifisa.board.domain.member.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse foo(UnauthorizedException e) {
        log.info("==== exception");
        // log.error("", e);
        return ErrorResponse.fail(e.getExceptionStatus());
    }

}
