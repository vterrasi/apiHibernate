package com.cursoSpring.apiHibernate.infrastructure.persistence.entities;

import com.cursoSpring.apiHibernate.domain.enums.MovieGenre;
import com.cursoSpring.apiHibernate.domain.enums.MovieType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEntity {

    @Id
    private String id;

    private String title;

    @Enumerated(EnumType.STRING)
    private MovieGenre genre;

    private int releaseYear;

    private String director;

    private int runningTime;

    private int stock;

    private boolean available;

    @Enumerated(EnumType.STRING)
    private MovieType type;
}