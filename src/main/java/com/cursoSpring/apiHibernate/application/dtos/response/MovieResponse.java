package com.cursoSpring.apiHibernate.application.dtos.response;

import com.cursoSpring.apiHibernate.domain.enums.MovieGenre;
import com.cursoSpring.apiHibernate.domain.enums.MovieType;
import com.cursoSpring.apiHibernate.domain.models.MovieModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieResponse {

    private final String id;
    private final String title;
    private final MovieGenre genre;
    private final int releaseYear;
    private final String director;
    private final int runningTime;
    private final int stock;
    private final boolean available;
    private final MovieType type;

    public static MovieResponse from(MovieModel model) {
        return MovieResponse.builder()
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
}