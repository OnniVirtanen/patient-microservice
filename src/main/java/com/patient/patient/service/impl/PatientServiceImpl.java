package com.patient.patient.service.impl;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.PatientDTO;
import com.patient.patient.model.PatientDTOMapper;
import com.patient.patient.persistence.PatientRepository;
import com.patient.patient.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);
    private final PatientRepository patientRepository;
    private final PatientDTOMapper patientDTOMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientDTOMapper patientDTOMapper) {
        this.patientRepository = patientRepository;
        this.patientDTOMapper = patientDTOMapper;
    }

    @Override
    public List<PatientDTO> selectPatients() throws PatientServiceException {
        try {
            return patientRepository.findAll()
                    .stream()
                    .map(patientDTOMapper)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new PatientServiceException(e);
        }
    }
}
