package com.cursoSpring.apiHibernate.interfaces.rest.controllers;

import com.cursoSpring.apiHibernate.application.dtos.request.CreateRentalRequest;
import com.cursoSpring.apiHibernate.application.dtos.response.RentalResponse;
import com.cursoSpring.apiHibernate.application.services.RentalService;
import com.cursoSpring.apiHibernate.domain.models.RentalModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @GetMapping
    public List<RentalResponse> getAll() {
        return rentalService.findAll()
                .stream()
                .map(RentalResponse::from)
                .toList();
    }

    @PostMapping
    public ResponseEntity<RentalResponse> create(@RequestBody CreateRentalRequest request) {

        RentalModel rental = rentalService.createRental(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RentalResponse.from(rental));
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<RentalResponse> returnRental(@PathVariable String id) {

        RentalModel rental = rentalService.returnRental(id);

        return ResponseEntity.ok(RentalResponse.from(rental));
    }
}