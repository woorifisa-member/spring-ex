package com.woorifisa.board.domain.member.exception;

import com.woorifisa.board.common.exception.base.BaseAbstractException;
import com.woorifisa.board.common.exception.base.ExceptionStatus;

public class UnauthorizedException extends BaseAbstractException {

    public UnauthorizedException() {
        super(ExceptionStatus.LOGIN_FAIL);
    }

}
