package com.cursoSpring.apiHibernate.domain.ports;

import com.cursoSpring.apiHibernate.domain.models.RentalModel;

import java.util.List;
import java.util.Optional;

public interface RentRepositoryPort {
    List<RentalModel> findAll();
    Optional<RentalModel> findById(String id);
    RentalModel save(RentalModel RentalModel);
}