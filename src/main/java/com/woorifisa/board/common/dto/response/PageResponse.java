package com.woorifisa.board.common.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PageResponse<T> {

    private final int totalPage;
    private final int page;
    private final List<T> items;

    public static <T> PageResponse<T> success(int totalPage, int page, List<T> items) {
        return new PageResponse<>(totalPage, page, items);
    }

}
