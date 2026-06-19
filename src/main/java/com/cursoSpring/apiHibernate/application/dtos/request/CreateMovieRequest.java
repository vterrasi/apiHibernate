package com.cursoSpring.apiHibernate.application.dtos.request;

import com.cursoSpring.apiHibernate.domain.enums.MovieGenre;
import com.cursoSpring.apiHibernate.domain.enums.MovieType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovieRequest {

    private String title;
    private MovieGenre genre;
    private int releaseYear;
    private String director;
    private int runningTime;
    private int stock;
    private MovieType type;
}