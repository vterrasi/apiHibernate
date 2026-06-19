package com.cursoSpring.apiHibernate.application.services;

import com.cursoSpring.apiHibernate.application.dtos.request.CreateRentalRequest;
import com.cursoSpring.apiHibernate.application.validators.CreateRentalValidator;
import com.cursoSpring.apiHibernate.domain.enums.ErrorCode;
import com.cursoSpring.apiHibernate.domain.enums.MovieType;
import com.cursoSpring.apiHibernate.domain.enums.RentalStatus;
import com.cursoSpring.apiHibernate.domain.exceptions.DomainException;
import com.cursoSpring.apiHibernate.domain.models.MovieModel;
import com.cursoSpring.apiHibernate.domain.models.RentalModel;
import com.cursoSpring.apiHibernate.domain.ports.MovieRepositoryPort;
import com.cursoSpring.apiHibernate.domain.ports.RentalRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final MovieRepositoryPort movieRepository;
    private final RentalRepositoryPort rentalRepository;
    private final CreateRentalValidator validator;

    public RentalModel createRental(CreateRentalRequest request) {

        List<MovieModel> movies = request.getMovieIds().stream()
                .map(this::getMovieOrFail)
                .toList();

        validator.validate(movies);

        List<MovieModel> updatedMovies = movies.stream()
                .map(MovieModel::markAsUnavailable)
                .toList();

        movieRepository.saveAll(updatedMovies);

        double price = calculatePrice(movies);

        RentalModel rental = RentalModel.builder()
                .id(UUID.randomUUID().toString())
                .movieIds(request.getMovieIds())
                .price(price)
                .status(RentalStatus.ACTIVE)
                .userId(request.getUserId())
                .build();

        return rentalRepository.save(rental);
    }

    public RentalModel returnRental(String rentalId) {

        RentalModel rental = rentalRepository.findById(rentalId)
                .orElseThrow(() ->
                        new DomainException(ErrorCode.RENTAL_NOT_FOUND, "Rental not found"));

        if (rental.getStatus() == RentalStatus.RETURNED) {
            throw new DomainException(ErrorCode.RENTAL_ALREADY_RETURNED, "Rental already returned");
        }

        List<MovieModel> movies = rental.getMovieIds().stream()
                .map(this::getMovieOrFail)
                .toList();

        List<MovieModel> updatedMovies = movies.stream()
                .map(MovieModel::markAsAvailable)
                .toList();

        movieRepository.saveAll(updatedMovies);

        RentalModel updatedRental = RentalModel.builder()
                .id(rental.getId())
                .movieIds(rental.getMovieIds())
                .price(rental.getPrice())
                .userId(rental.getUserId())
                .status(RentalStatus.RETURNED)
                .build();

        return rentalRepository.save(updatedRental);
    }

    private MovieModel getMovieOrFail(String id) {
        return movieRepository.findById(id)
                .orElseThrow(() ->
                        new DomainException(ErrorCode.MOVIE_NOT_FOUND, "Movie not found"));
    }

    private double calculatePrice(List<MovieModel> movies) {
        return movies.stream()
                .mapToDouble(movie ->
                        movie.getType() == MovieType.NEW_RELEASE ? 5.0 : 3.0
                )
                .sum();
    }

    public List<RentalModel> findAll() {
        return rentalRepository.findAll();
    }
}