package com.patient.patient.service;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.NewPatientRequest;
import com.patient.patient.model.PatientDTO;
import com.patient.patient.model.PatientRemoveResponse;
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
     *
     * @return A list of {@link PatientDTO} representing all the patients.
     * @throws PatientServiceException if there's any issue fetching the patients.
     */
    List<PatientDTO> selectPatients() throws PatientServiceException;

    /**
     * Creates a new patient record.
     *
     * @param patient The {@link PatientEntity} object with patient details.
     * @return The created {@link PatientDTO} object with patient details.
     * @throws PatientServiceException if there's any issue creating the patient.
     */
    PatientDTO createPatient(PatientEntity patient) throws PatientServiceException;

    /**
     * Updates an existing patient record.
     *
     * @param patientRequest The {@link NewPatientRequest} containing the updated patient details.
     * @param id The UUID identifier of the patient to be updated.
     * @return An {@link Optional} containing the updated {@link PatientDTO} if successful; empty otherwise.
     * @throws PatientServiceException if there's any issue updating the patient.
     */
    Optional<PatientDTO> updatePatient(NewPatientRequest patientRequest, UUID id) throws PatientServiceException;

    /**
     * Removes a patient record.
     *
     * @param id The UUID identifier of the patient to be removed.
     * @return A {@link PatientRemoveResponse} object indicating the removal outcome.
     * @throws PatientServiceException if there's any issue removing the patient.
     */
    PatientRemoveResponse removePatient(UUID id) throws PatientServiceException;
}
