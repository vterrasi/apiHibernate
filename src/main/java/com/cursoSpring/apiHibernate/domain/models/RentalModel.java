package com.cursoSpring.apiHibernate.domain.models;

import com.cursoSpring.apiHibernate.domain.enums.RentalStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RentalModel {

    private final String id;
    private final List<String> movieIds;
    private final RentalStatus status;
    private final Double price;
    private final String userId;
}