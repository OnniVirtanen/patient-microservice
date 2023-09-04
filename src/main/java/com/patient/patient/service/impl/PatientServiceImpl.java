package com.patient.patient.service.impl;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.NewPatientRequest;
import com.patient.patient.model.PatientDTO;
import com.patient.patient.model.PatientDTOMapper;
import com.patient.patient.model.PatientRemoveResponse;
import com.patient.patient.persistence.PatientEntity;
import com.patient.patient.persistence.PatientRepository;
import com.patient.patient.service.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private static final String SELECT_PATIENTS_ERR_MSG = "Failure in selecting patients.";
    private static final String CREATE_PATIENT_ERR_MSG = "Failure in creating patient.";
    private static final String UPDATE_PATIENT_ERR_MSG = "Failure in updating patient.";
    private static final String REMOVE_PATIENT_ERR_MSG = "Failure in removing patient.";
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
        } catch (Exception exception) {
            throw new PatientServiceException(SELECT_PATIENTS_ERR_MSG, exception);
        }
    }

    @Override
    @Transactional
    public PatientDTO createPatient(final PatientEntity patient) throws PatientServiceException {
        try {
            PatientEntity savedPatient = patientRepository.save(patient);
            return patientDTOMapper.apply(savedPatient);
        } catch (Exception exception) {
            throw new PatientServiceException(CREATE_PATIENT_ERR_MSG, exception);
        }
    }

    @Override
    @Transactional
    public Optional<PatientDTO> updatePatient(final NewPatientRequest patientRequest, final UUID id)
            throws PatientServiceException {
        try {
            Optional<PatientEntity> optionalPatient = patientRepository.findById(id);
            boolean patientIsPresent = optionalPatient.isPresent();

            if (patientIsPresent) {
                PatientEntity patient = optionalPatient.get();
                patient.setFirstName(patientRequest.firstName());
                patient.setLastName(patientRequest.secondName());
                patient.setSecondName(patientRequest.lastName());
                patient.setGender(patientRequest.gender());
                patient.setSSN(patientRequest.SSN());
                patient.setDateOfBirth(patientRequest.dateOfBirth());

                PatientEntity updatedPatient = patientRepository.save(patient);
                return Optional.of(patientDTOMapper.apply(updatedPatient));
            }
            else {
                return Optional.empty();
            }
        } catch (Exception exception) {
            throw new PatientServiceException(UPDATE_PATIENT_ERR_MSG, exception);
        }
    }

    @Override
    @Transactional
    public PatientRemoveResponse removePatient(final UUID patientId) throws PatientServiceException {
        try {
            Optional<PatientEntity> optionalPatient = patientRepository.findById(patientId);
            boolean patientIsPresent = optionalPatient.isPresent();

            if (patientIsPresent) {
                patientRepository.deleteById(patientId);
            }

            PatientRemoveResponse response = new PatientRemoveResponse();
            response.setPatientWasFound(patientIsPresent);
            return response;
        } catch (Exception exception) {
            throw new PatientServiceException(REMOVE_PATIENT_ERR_MSG, exception);
        }
    }
}
