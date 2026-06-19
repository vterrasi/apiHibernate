package com.cursoSpring.apiHibernate.application.validators;

import com.cursoSpring.apiHibernate.application.dtos.request.CreateMovieRequest;
import com.cursoSpring.apiHibernate.domain.exceptions.DomainException;
import com.cursoSpring.apiHibernate.domain.enums.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class CreateMovieValidator {

    public void validate(CreateMovieRequest request) {

        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new DomainException(ErrorCode.INVALID_REQUEST, "Title required");
        }

        if (request.getStock() < 0) {
            throw new DomainException(ErrorCode.INVALID_REQUEST, "Stock cannot be negative");
        }
    }
}