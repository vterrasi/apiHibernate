package com.cursoSpring.apiHibernate.application.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
    private List<String> movieIds;
    private String userId;
}