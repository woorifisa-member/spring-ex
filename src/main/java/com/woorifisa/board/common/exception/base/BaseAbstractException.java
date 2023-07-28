package com.woorifisa.board.common.exception.base;

public class BaseAbstractException extends RuntimeException {

    protected final ExceptionStatus exceptionStatus;

    public BaseAbstractException(ExceptionStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

}
