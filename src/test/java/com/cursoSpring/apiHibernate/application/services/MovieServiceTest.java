package com.cursoSpring.apiHibernate.application.services;

import com.cursoSpring.apiHibernate.application.dtos.request.CreateMovieRequest;
import com.cursoSpring.apiHibernate.application.validators.CreateMovieValidator;
import com.cursoSpring.apiHibernate.domain.enums.MovieGenre;
import com.cursoSpring.apiHibernate.domain.enums.MovieType;
import com.cursoSpring.apiHibernate.domain.models.MovieModel;
import com.cursoSpring.apiHibernate.domain.ports.MovieRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepositoryPort movieRepository;

    @Mock
    private CreateMovieValidator validator;

    @InjectMocks
    private MovieService movieService;

    @Test
    void shouldCreateMovieSuccessfully() {

        CreateMovieRequest request = new CreateMovieRequest(
                "Interstellar",
                MovieGenre.SCIFI,
                2014,
                "Nolan",
                180,
                10,
                MovieType.NEW_RELEASE
        );

        MovieModel savedMovie = MovieModel.builder()
                .id("1")
                .title("Interstellar")
                .genre(MovieGenre.SCIFI)
                .releaseYear(2014)
                .director("Nolan")
                .runningTime(180)
                .stock(10)
                .available(true)
                .type(MovieType.NEW_RELEASE)
                .build();

        Mockito.when(movieRepository.save(Mockito.any()))
                .thenReturn(savedMovie);

        MovieModel result = movieService.createMovie(request);

        assertEquals("Interstellar", result.getTitle());
    }
}