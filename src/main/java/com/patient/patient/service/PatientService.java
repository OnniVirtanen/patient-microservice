package com.patient.patient.service;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.NewPatientRequest;
import com.patient.patient.model.PatientDTO;
import com.patient.patient.model.PatientRemoveResponse;
import com.patient.patient.persistence.PatientEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientService {
    List<PatientDTO> selectPatients() throws PatientServiceException;

    PatientDTO createPatient(PatientEntity patient) throws PatientServiceException;

    Optional<PatientDTO> updatePatient(NewPatientRequest patientRequest, UUID id) throws PatientServiceException;

    PatientRemoveResponse removePatient(UUID id) throws PatientServiceException;
}
