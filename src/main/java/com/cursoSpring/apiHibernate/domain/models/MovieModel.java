package com.cursoSpring.apiHibernate.domain.models;

import com.cursoSpring.apiHibernate.domain.enums.MovieGenre;
import com.cursoSpring.apiHibernate.domain.enums.MovieType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class MovieModel {

    private final String id;
    private final String title;
    private final MovieGenre genre;
    private final int releaseYear;
    private final String director;
    private final int runningTime;
    private final int stock;
    private final boolean available;
    private final MovieType type;

    public MovieModel markAsUnavailable() {
        return this.toBuilder()
                .available(false)
                .build();
    }

    public MovieModel markAsAvailable() {
        return this.toBuilder()
                .available(true)
                .build();
    }

    public boolean isAvailableForRent() {
        return available && stock > 0;
    }
}