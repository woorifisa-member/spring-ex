package com.woorifisa.board.domain.member.exception;

import com.woorifisa.board.common.exception.base.BaseAbstractException;
import com.woorifisa.board.common.exception.base.ExceptionStatus;

public class MemberNotFoundException extends BaseAbstractException {

    public MemberNotFoundException() {
        super(ExceptionStatus.MEMBER_NOT_FOUND);
    }
}
