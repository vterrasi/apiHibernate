package com.cursoSpring.apiHibernate.infrastructure.persistence.adapters;

import com.cursoSpring.apiHibernate.application.mappers.MovieMapper;
import com.cursoSpring.apiHibernate.domain.models.MovieModel;
import com.cursoSpring.apiHibernate.domain.ports.MovieRepositoryPort;
import com.cursoSpring.apiHibernate.infrastructure.persistence.repositories.MovieJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepositoryPort {

    private final MovieJpaRepository jpaRepository;

    @Override
    public MovieModel save(MovieModel movie) {
        return MovieMapper.toDomain(
                jpaRepository.save(MovieMapper.toEntity(movie))
        );
    }

    @Override
    public Optional<MovieModel> findById(String id) {
        return jpaRepository.findById(id)
                .map(MovieMapper::toDomain);
    }

    @Override
    public List<MovieModel> findAllByIds(List<String> ids) {
        return jpaRepository.findAllByIdIn(ids)
                .stream()
                .map(MovieMapper::toDomain)
                .toList();
    }

    @Override
    public List<MovieModel> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(MovieMapper::toDomain)
                .toList();
    }

    @Override
    public List<MovieModel> saveAll(List<MovieModel> movies) {
        return jpaRepository.saveAll(
                movies.stream().map(MovieMapper::toEntity).toList()
        ).stream().map(MovieMapper::toDomain).toList();
    }
    @Override
    public List<MovieModel> findAllAvailable() {
        return jpaRepository.findAllByAvailableTrue()
                .stream()
                .map(MovieMapper::toDomain)
                .toList();
    }
}