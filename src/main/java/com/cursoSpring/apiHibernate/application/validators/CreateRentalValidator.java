package com.cursoSpring.apiHibernate.application.validators;

import com.cursoSpring.apiHibernate.domain.exceptions.DomainException;
import com.cursoSpring.apiHibernate.domain.enums.ErrorCode;
import com.cursoSpring.apiHibernate.domain.models.MovieModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateRentalValidator {

    public void validate(List<MovieModel> movies) {

        if (movies == null || movies.isEmpty()) {
            throw new DomainException(ErrorCode.INVALID_REQUEST, "No movies selected");
        }

        boolean unavailable = movies.stream()
                .anyMatch(m -> !m.isAvailableForRent());

        if (unavailable) {
            throw new DomainException(ErrorCode.MOVIE_NOT_AVAILABLE, "Some movies are not available");
        }
    }
}