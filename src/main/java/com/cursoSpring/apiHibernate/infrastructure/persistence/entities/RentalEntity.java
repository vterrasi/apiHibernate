package com.cursoSpring.apiHibernate.infrastructure.persistence.entities;

import com.cursoSpring.apiHibernate.domain.enums.RentalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "rentals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalEntity {

    @Id
    private String id;

    @ElementCollection
    private List<String> movieIds;

    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    private Double price;

    private String userId;
}
