package com.woorifisa.board.domain.member.exception;

import com.woorifisa.board.common.exception.base.BaseAbstractException;
import com.woorifisa.board.common.exception.base.ExceptionStatus;

public class ForbiddenException extends BaseAbstractException {

    public ForbiddenException() {
        super(ExceptionStatus.FORBIDDEN);
    }

}
