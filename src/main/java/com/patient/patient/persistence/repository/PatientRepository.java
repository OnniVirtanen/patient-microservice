package com.patient.patient.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, UUID> {

    /**
     * Determines whether a patient exists with the specified Social Security Number (SSN).
     *
     * @param SSN The Social Security Number of the patient to check.
     * @return {@code true} if a patient with the given SSN exists, {@code false} otherwise.
     */
    @Query("" +
            "SELECT CASE WHEN COUNT(p) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Patient p " +
            "WHERE p.SSN = ?1"
    )
    Boolean existsBySSN(String SSN);
}
