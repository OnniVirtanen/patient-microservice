package com.patient.patient.service.impl;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.*;
import com.patient.patient.persistence.PatientEntity;
import com.patient.patient.persistence.PatientRepository;
import com.patient.patient.service.PatientService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Override
    public PatientDTO createPatient(final PatientEntity patient) throws PatientServiceException {
        try {
            final PatientEntity savedPatient = patientRepository.save(patient);
            return patientDTOMapper.apply(savedPatient);
        } catch (Exception e) {
            throw new PatientServiceException("Failure in saving patient.", e);
        }
    }

    @Override
    public Optional<PatientDTO> updatePatient(final NewPatientRequest patientRequest, final UUID id) throws PatientServiceException {
        try {
            final Optional<PatientEntity> optionalPatient = patientRepository.findById(id);

            if (optionalPatient.isPresent()) {
                final PatientEntity patient = optionalPatient.get();
                patient.setFirstName(patientRequest.firstName());
                patient.setLastName(patientRequest.secondName());
                patient.setSecondName(patientRequest.lastName());
                patient.setGender(patientRequest.gender());
                patient.setSSN(patientRequest.SSN());
                patient.setDateOfBirth(patientRequest.dateOfBirth());

                try {
                     final PatientEntity updatedPatient = patientRepository.save(patient);
                     return Optional.of(patientDTOMapper.apply(updatedPatient));
                } catch (Exception e) {
                    throw new PatientServiceException("Failure in saving patient.", e);
                }
            }
            else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new PatientServiceException(e);
        }
    }

    @Override
    @Transactional
    public PatientRemoveResponse removePatient(final UUID id) throws PatientServiceException {
        final PatientRemoveResponse response = new PatientRemoveResponse();
        final Optional<PatientEntity> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            try {
                patientRepository.deleteById(id);
            } catch (Exception e) {
                throw new PatientServiceException("Failure in deleting patient");
            }
            response.setPatientWasFound(true);
        }
        else {
            response.setPatientWasFound(false);
        }
        return response;
    }
}
