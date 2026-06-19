package com.cursoSpring.apiHibernate.domain.ports;

import com.cursoSpring.apiHibernate.domain.models.MovieModel;

import java.util.List;
import java.util.Optional;

public interface MovieRepositoryPort {
    List<MovieModel> findAll();
    Optional<MovieModel> findById(String id);
    List<MovieModel> findAllByIds(List<String> ids);
    MovieModel save(MovieModel MovieModel);
    List<MovieModel> saveAll(List<MovieModel> MovieModels);
    List<MovieModel> findAllAvailable();
}