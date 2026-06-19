package com.cursoSpring.apiHibernate.application.services;

import com.cursoSpring.apiHibernate.application.dtos.request.CreateRentalRequest;
import com.cursoSpring.apiHibernate.application.validators.CreateRentalValidator;
import com.cursoSpring.apiHibernate.domain.enums.MovieType;
import com.cursoSpring.apiHibernate.domain.enums.RentalStatus;
import com.cursoSpring.apiHibernate.domain.models.MovieModel;
import com.cursoSpring.apiHibernate.domain.models.RentalModel;
import com.cursoSpring.apiHibernate.domain.ports.MovieRepositoryPort;
import com.cursoSpring.apiHibernate.domain.ports.RentRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

    @Mock
    private MovieRepositoryPort movieRepository;

    @Mock
    private RentRepositoryPort rentRepository;

    @Mock
    private CreateRentalValidator validator;

    @InjectMocks
    private RentalService rentalService;

    @Test
    void shouldCreateRentalSuccessfully() {

        MovieModel movie = MovieModel.builder()
                .id("1")
                .title("Movie")
                .type(MovieType.STANDARD)
                .available(true)
                .stock(5)
                .build();

        Mockito.when(movieRepository.findById("1"))
                .thenReturn(Optional.of(movie));

        Mockito.doNothing().when(validator).validate(Mockito.anyList());

        Mockito.when(movieRepository.saveAll(Mockito.any()))
                .thenReturn(List.of(movie));

        Mockito.when(rentRepository.save(Mockito.any()))
                .thenAnswer(i -> i.getArgument(0));

        CreateRentalRequest request = new CreateRentalRequest(
                List.of("1"),
                "user1"
        );

        RentalModel result = rentalService.createRental(request);

        assertEquals(3.0, result.getPrice());
        assertEquals(RentalStatus.ACTIVE, result.getStatus());
    }
}