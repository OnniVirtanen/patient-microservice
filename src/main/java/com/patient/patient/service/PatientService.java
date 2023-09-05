package com.patient.patient.service;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.NewPatientRequest;
import com.patient.patient.model.PatientDTO;
import com.patient.patient.model.PatientDeleteResponse;
import com.patient.patient.persistence.PatientEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface to handle operations related to patients.
 */
public interface PatientService {

    /**
     * Retrieves a list of all patients.
     */
    List<PatientDTO> getAllPatients() throws PatientServiceException;

    /**
     * Creates a new patient record.
     */
    PatientDTO createPatient(NewPatientRequest newPatient) throws PatientServiceException;

    /**
     * Updates an existing patient record.
     */
    Optional<PatientDTO> updatePatient(NewPatientRequest patientRequest, UUID id) throws PatientServiceException;

    /**
     * Removes a patient record.
     */
    PatientDeleteResponse deletePatient(UUID patientId) throws PatientServiceException;
}
