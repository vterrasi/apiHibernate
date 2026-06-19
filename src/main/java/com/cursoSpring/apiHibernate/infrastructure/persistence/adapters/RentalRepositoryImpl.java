package com.cursoSpring.apiHibernate.infrastructure.persistence.adapters;

import com.cursoSpring.apiHibernate.application.mappers.RentalMapper;
import com.cursoSpring.apiHibernate.domain.models.RentalModel;
import com.cursoSpring.apiHibernate.domain.ports.RentRepositoryPort;
import com.cursoSpring.apiHibernate.infrastructure.persistence.repositories.RentalJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RentalRepositoryImpl implements RentRepositoryPort {

    private final RentalJpaRepository jpaRepository;

    @Override
    public RentalModel save(RentalModel rental) {
        return RentalMapper.toDomain(
                jpaRepository.save(RentalMapper.toEntity(rental))
        );
    }

    @Override
    public Optional<RentalModel> findById(String id) {
        return jpaRepository.findById(id)
                .map(RentalMapper::toDomain);
    }
    @Override
    public List<RentalModel> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(RentalMapper::toDomain)
                .toList();
    }
}
