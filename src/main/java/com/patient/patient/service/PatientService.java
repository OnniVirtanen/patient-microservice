package com.patient.patient.service;

import com.patient.patient.model.NewPatientRequest;
import com.patient.patient.model.PatientDTO;

import java.util.List;
import java.util.UUID;

public interface PatientService {

    List<PatientDTO> getAllPatients();

    PatientDTO createPatient(NewPatientRequest newPatient);

    PatientDTO updatePatient(NewPatientRequest patientRequest, UUID id);

    void deletePatient(UUID patientId);

    PatientDTO getPatientById(UUID patientId);
}
