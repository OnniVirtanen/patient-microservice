package com.patient.patient.model;


import java.time.LocalDate;

public record NewPatientRequest(
        String firstName,
        String secondName,
        String lastName,
        GenderEnum gender,
        String SSN,
        LocalDate dateOfBirth
) {
}
