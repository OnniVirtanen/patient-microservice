package com.patient.patient.service.impl;

import com.patient.patient.exception.PatientAlreadyExistsException;
import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.exception.PatientNotFoundException;
import com.patient.patient.model.NewPatientRequest;
import com.patient.patient.model.PatientDTO;
import com.patient.patient.model.PatientDTOMapper;
import com.patient.patient.persistence.PatientEntity;
import com.patient.patient.persistence.PatientRepository;
import com.patient.patient.service.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.patient.patient.model.Constants.*;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientDTOMapper patientDTOMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientDTOMapper patientDTOMapper) {
        this.patientRepository = patientRepository;
        this.patientDTOMapper = patientDTOMapper;
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        try {
            return patientRepository.findAll()
                    .stream()
                    .map(patientDTOMapper)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new PatientServiceException(GET_ALL_PATIENTS_ERR_MSG, exception);
        }
    }

    @Override
    @Transactional
    public PatientDTO createPatient(final NewPatientRequest newPatient) {
        ensurePatientDoesNotExist(newPatient.SSN());
        try {
            PatientEntity savedPatient = patientRepository.save(new PatientEntity(newPatient));
            return patientDTOMapper.apply(savedPatient);
        } catch (Exception exception) {
            throw new PatientServiceException(CREATE_PATIENT_ERR_MSG, exception);
        }
    }

    @Override
    @Transactional
    public PatientDTO updatePatient(final NewPatientRequest newPatientRequest, final UUID id) {
        Optional<PatientEntity> patientFromDB = patientRepository.findById(id);
        if (patientFromDB.isEmpty()) {
            throw new PatientNotFoundException(NO_PATIENT_FOUND_ERR_MSG);
        }

        try {
            PatientEntity patient = PatientEntity.modifyPatient(newPatientRequest, patientFromDB.get());
            PatientEntity updatedPatient = patientRepository.save(patient);
            return patientDTOMapper.apply(updatedPatient);
        } catch (Exception exception) {
            throw new PatientServiceException(UPDATE_PATIENT_ERR_MSG, exception);
        }
    }

    @Override
    @Transactional
    public void deletePatient(final UUID patientId) {
        try {
            Optional<PatientEntity> patientFromDB = patientRepository.findById(patientId);
            if (patientFromDB.isEmpty()) {
                throw new PatientNotFoundException(NO_PATIENT_FOUND_ERR_MSG);
            }
            patientRepository.deleteById(patientId);
        } catch (PatientNotFoundException patientNotFoundException) {
          throw patientNotFoundException;
        } catch (Exception exception) {
            throw new PatientServiceException(DELETE_PATIENT_ERR_MSG, exception);
        }
    }

    private void ensurePatientDoesNotExist(final String ssn) {
        if (patientRepository.existsBySSN(ssn)) {
            throw new PatientAlreadyExistsException(PATIENT_ALREADY_EXISTS_ERR_MSG);
        }
    }
}
