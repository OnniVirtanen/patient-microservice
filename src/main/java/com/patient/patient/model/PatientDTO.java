package com.patient.patient.model;

import com.patient.patient.persistence.AddressEntity;
import com.patient.patient.persistence.EmergencyContactEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public record PatientDTO(

        GenderEnum gender,
        String firstName,
        String secondName,
        String lastName,
        LocalDate dateOfBirth,
        List<AddressEntity> addresses,
        List<EmergencyContactEntity> emergencyContactEntities

) {
}
