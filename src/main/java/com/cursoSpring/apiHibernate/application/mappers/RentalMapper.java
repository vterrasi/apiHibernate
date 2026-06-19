package com.cursoSpring.apiHibernate.application.mappers;

import com.cursoSpring.apiHibernate.domain.models.RentalModel;
import com.cursoSpring.apiHibernate.infrastructure.persistence.entities.RentalEntity;

public class RentalMapper {

    // ENTITY → DOMAIN
    public static RentalModel toDomain(RentalEntity entity) {
        if (entity == null) return null;

        return RentalModel.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .status(entity.getStatus())
                .price(entity.getPrice())
                .movieIds(entity.getMovieIds()) // si existe en tu entity
                .build();
    }

    // DOMAIN → ENTITY
    public static RentalEntity toEntity(RentalModel model) {
        if (model == null) return null;

        return RentalEntity.builder()
                .id(model.getId())
                .userId(model.getUserId())
                .status(model.getStatus())
                .price(model.getPrice())
                .movieIds(model.getMovieIds()) // si existe en tu model
                .build();
    }
}
