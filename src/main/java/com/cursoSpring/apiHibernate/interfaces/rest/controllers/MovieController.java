package com.cursoSpring.apiHibernate.interfaces.rest.controllers;

import com.cursoSpring.apiHibernate.application.dtos.request.CreateMovieRequest;
import com.cursoSpring.apiHibernate.application.dtos.response.MovieResponse;
import com.cursoSpring.apiHibernate.application.services.MovieService;
import com.cursoSpring.apiHibernate.domain.models.MovieModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/available")
    public List<MovieResponse> getAvailableMovies() {
        return movieService.findAllAvailable()
                .stream()
                .map(MovieResponse::from)
                .toList();
    }

    @PostMapping
    public ResponseEntity<MovieResponse> create(@RequestBody CreateMovieRequest request) {

        MovieModel movie = movieService.createMovie(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MovieResponse.from(movie));
    }
}