package com.patient.patient.service.impl;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.*;
import com.patient.patient.persistence.PatientEntity;
import com.patient.patient.persistence.PatientRepository;
import com.patient.patient.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    public void createPatient(final PatientEntity patient) throws PatientServiceException {
        try {
            patientRepository.save(patient);
        } catch (Exception e) {
            throw new PatientServiceException(e);
        }
    }

    @Override
    public Response updatePatient(final NewPatientRequest patientRequest, final UUID id) throws PatientServiceException {
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
                patientRepository.save(patient);
            } catch (Exception e) {
                throw new PatientServiceException("Failure in saving patient.", e);
            }

            return new SuccessResponse("Patient updated successfully.");
        }
        else {
            return new FailureResponse("Patient not found.");
        }
    }
}
