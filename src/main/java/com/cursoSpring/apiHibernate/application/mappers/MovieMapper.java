package com.cursoSpring.apiHibernate.application.mappers;

import com.cursoSpring.apiHibernate.application.dtos.request.CreateMovieRequest;
import com.cursoSpring.apiHibernate.domain.models.MovieModel;
import com.cursoSpring.apiHibernate.infrastructure.persistence.entities.MovieEntity;

import java.util.UUID;

public class MovieMapper {

    // ENTITY → DOMAIN
    public static MovieModel toDomain(MovieEntity entity) {
        if (entity == null) return null;

        return MovieModel.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .genre(entity.getGenre())
                .releaseYear(entity.getReleaseYear())
                .director(entity.getDirector())
                .runningTime(entity.getRunningTime())
                .stock(entity.getStock())
                .available(entity.isAvailable())
                .type(entity.getType())
                .build();
    }

    // DOMAIN → ENTITY
    public static MovieEntity toEntity(MovieModel model) {
        if (model == null) return null;

        return MovieEntity.builder()
                .id(model.getId())
                .title(model.getTitle())
                .genre(model.getGenre())
                .releaseYear(model.getReleaseYear())
                .director(model.getDirector())
                .runningTime(model.getRunningTime())
                .stock(model.getStock())
                .available(model.isAvailable())
                .type(model.getType())
                .build();
    }

    // REQUEST → DOMAIN
    public static MovieModel fromRequest(CreateMovieRequest request) {
        return MovieModel.builder()
                .id(UUID.randomUUID().toString())
                .title(request.getTitle())
                .genre(request.getGenre())
                .releaseYear(request.getReleaseYear())
                .director(request.getDirector())
                .runningTime(request.getRunningTime())
                .stock(request.getStock())
                .available(true) // regla de negocio
                .type(request.getType())
                .build();
    }
}