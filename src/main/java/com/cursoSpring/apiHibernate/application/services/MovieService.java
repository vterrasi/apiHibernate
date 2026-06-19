package com.cursoSpring.apiHibernate.application.services;

import com.cursoSpring.apiHibernate.application.dtos.request.CreateMovieRequest;
import com.cursoSpring.apiHibernate.application.mappers.MovieMapper;
import com.cursoSpring.apiHibernate.application.validators.CreateMovieValidator;
import com.cursoSpring.apiHibernate.domain.enums.ErrorCode;
import com.cursoSpring.apiHibernate.domain.exceptions.DomainException;
import com.cursoSpring.apiHibernate.domain.models.MovieModel;
import com.cursoSpring.apiHibernate.domain.ports.MovieRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepositoryPort movieRepository;
    private final CreateMovieValidator validator;

    public MovieModel createMovie(CreateMovieRequest request) {

        validator.validate(request);

        MovieModel movie = MovieMapper.fromRequest(request);

        return movieRepository.save(movie);
    }

    public List<MovieModel> findAll() {
        return movieRepository.findAll();
    }

    public MovieModel findById(String id) {
        return movieRepository.findById(id)
                .orElseThrow(() ->
                        new DomainException(ErrorCode.MOVIE_NOT_FOUND, "Movie not found"));
    }

    public MovieModel updateAvailability(MovieModel movie) {
        return movieRepository.save(movie);
    }

    public List<MovieModel> findAllAvailable() {
        return movieRepository.findAllAvailable();
    }
}