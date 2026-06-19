package com.cursoSpring.apiHibernate.infrastructure.persistence.repositories;

import com.cursoSpring.apiHibernate.infrastructure.persistence.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, String> {

    List<MovieEntity> findAllByIdIn(List<String> ids);
    List<MovieEntity> findAllByAvailableTrue();
}