package com.patient.patient.model;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a Data Transfer Object (DTO) for a patient. This DTO encapsulates
 * essential patient information for transferring data between different layers of an application.
 * Java's record feature is used for concise data carrier representation.
 */
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
