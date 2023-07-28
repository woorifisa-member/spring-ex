package com.woorifisa.board.common.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@Getter
public class CommonResponse<T> {

    private final int code;
    private final T data;

    public static <T> ResponseEntity<CommonResponse<T>> success(HttpStatus status, int code, T data) {
        return ResponseEntity.status(status)
                             .body(new CommonResponse<>(code, data));
    }

}
