package com.cursoSpring.apiHibernate.application.dtos.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private final String code;
    private final String message;
    private final int status;
}