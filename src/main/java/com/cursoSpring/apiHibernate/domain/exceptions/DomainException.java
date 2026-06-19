package com.cursoSpring.apiHibernate.domain.exceptions;

import com.cursoSpring.apiHibernate.domain.enums.ErrorCode;

public class DomainException extends RuntimeException {

    private final ErrorCode errorCode;

    public DomainException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}