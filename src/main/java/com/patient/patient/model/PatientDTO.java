package com.patient.patient.model;

import java.time.LocalDate;
import java.util.UUID;


public record PatientDTO(
        UUID id,
        GenderEnum gender,
        String firstName,
        String secondName,
        String lastName,
        LocalDate dateOfBirth,
        String SSN
) {
}
