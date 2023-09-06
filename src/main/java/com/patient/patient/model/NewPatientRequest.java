package com.patient.patient.model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

/**
 * Represents a request to create a new patient. This record encapsulates the necessary
 * details required to register a new patient.
 */
public record NewPatientRequest(

        @NotEmpty
        String firstName,

        @NotEmpty
        String secondName,

        @NotEmpty
        String lastName,

        @NonNull
        GenderEnum gender,

        @NotEmpty
        String SSN,

        @NonNull
        LocalDate dateOfBirth
) {
}
