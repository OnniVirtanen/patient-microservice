package com.patient.patient.model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

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
