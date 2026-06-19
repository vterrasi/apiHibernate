package com.cursoSpring.apiHibernate.application.dtos.response;

import com.cursoSpring.apiHibernate.domain.enums.RentalStatus;
import com.cursoSpring.apiHibernate.domain.models.RentalModel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RentalResponse {

    private final String id;
    private final List<String> movieIds;
    private final Double price;
    private final RentalStatus status;

    public static RentalResponse from(RentalModel model) {
        return RentalResponse.builder()
                .id(model.getId())
                .movieIds(model.getMovieIds())
                .price(model.getPrice())
                .status(model.getStatus())
                .build();
    }
}
