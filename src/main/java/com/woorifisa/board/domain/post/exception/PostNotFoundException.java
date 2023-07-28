package com.woorifisa.board.domain.post.exception;

import com.woorifisa.board.common.exception.base.BaseAbstractException;
import com.woorifisa.board.common.exception.base.ExceptionStatus;

public class PostNotFoundException extends BaseAbstractException {

    public PostNotFoundException() {
        super(ExceptionStatus.POST_NOT_FOUND);
    }

}
