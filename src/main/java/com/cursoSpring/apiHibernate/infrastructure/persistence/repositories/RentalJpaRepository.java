package com.cursoSpring.apiHibernate.infrastructure.persistence.repositories;

import com.cursoSpring.apiHibernate.infrastructure.persistence.entities.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalJpaRepository extends JpaRepository<RentalEntity, String> {
}