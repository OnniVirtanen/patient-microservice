package com.patient.patient.service;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.NewPatientRequest;
import com.patient.patient.model.PatientDTO;
import com.patient.patient.model.Response;
import com.patient.patient.persistence.PatientEntity;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    List<PatientDTO> selectPatients() throws PatientServiceException;

    void createPatient(PatientEntity patient) throws PatientServiceException;

    Response updatePatient(NewPatientRequest patientRequest, UUID id) throws PatientServiceException;
}
