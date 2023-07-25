package com.patient.patient.service;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.PatientDTO;

import java.util.List;

public interface PatientService {
    List<PatientDTO> selectPatients() throws PatientServiceException;
}
