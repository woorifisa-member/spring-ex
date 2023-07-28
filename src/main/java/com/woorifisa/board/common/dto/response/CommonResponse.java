package com.woorifisa.board.common.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommonResponse<T> {

    private final int code;
    private final T data;

    public CommonResponse<T> success(int code, T data) {
        return new CommonResponse<>(code, data);
    }

}
