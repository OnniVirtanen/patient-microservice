package com.patient.patient.service.impl;

import com.patient.patient.exception.PatientAlreadyExistsException;
import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.exception.PatientNotFoundException;
import com.patient.patient.model.NewPatientRequest;
import com.patient.patient.model.PatientDTO;
import com.patient.patient.model.PatientDTOMapper;
import com.patient.patient.domain.PatientEntity;
import com.patient.patient.persistence.PatientRepository;
import com.patient.patient.service.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.patient.patient.model.ErrorMessage.*;

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
        return getAllPatientsOrThrow();
    }

    @Override
    @Transactional
    public PatientDTO createPatient(final NewPatientRequest newPatientRequest) {
        ensurePatientDoesNotExistBySSN(newPatientRequest.SSN());
        PatientEntity newPatient = new PatientEntity(newPatientRequest);
        return savePatientOrThrow(newPatient);
    }

    @Override
    @Transactional
    public PatientDTO updatePatient(final NewPatientRequest newPatientRequest, final UUID patientId) {
        PatientEntity patient = getPatientByIdOrThrow(patientId);
        patient.modifyPatient(newPatientRequest);
        return savePatientOrThrow(patient);
    }

    @Override
    @Transactional
    public void deletePatient(final UUID patientId) {
        ensurePatientIsPresent(patientId);
        deletePatientOrThrow(patientId);
    }

    @Override
    public PatientDTO getPatientById(final UUID patientId) {
        PatientEntity patientFromDB = getPatientByIdOrThrow(patientId);
        return patientDTOMapper.apply(patientFromDB);
    }

    private void deletePatientOrThrow(final UUID patientId) {
        try {
            patientRepository.deleteById(patientId);
        } catch (Exception exception) {
            throw new PatientServiceException(DELETE_PATIENT_ERR_MSG.getMessage(), exception);
        }
    }

    private void ensurePatientIsPresent(final UUID patientId) {
        boolean isPatientPresent = patientRepository.existsById(patientId);
        if (!isPatientPresent) {
            throw new PatientNotFoundException(NO_PATIENT_FOUND_ERR_MSG.getMessage());
        }
    }

    private void ensurePatientDoesNotExistBySSN(final String ssn) {
        if (patientRepository.existsBySSN(ssn)) {
            throw new PatientAlreadyExistsException(PATIENT_ALREADY_EXISTS_ERR_MSG.getMessage());
        }
    }

    private PatientEntity getPatientByIdOrThrow(final UUID patientId) {
        Optional<PatientEntity> patientFromDB = patientRepository.findById(patientId);
        return patientFromDB.orElseThrow(() -> new PatientNotFoundException(NO_PATIENT_FOUND_ERR_MSG.getMessage()));
    }

    private PatientDTO savePatientOrThrow(final PatientEntity patient) {
        try {
            PatientEntity savedPatient = patientRepository.save(patient);
            return patientDTOMapper.apply(savedPatient);
        } catch (Exception exception) {
            throw new PatientServiceException(SAVE_PATIENT_ERR_MSG.getMessage(), exception);
        }
    }

    private List<PatientDTO> getAllPatientsOrThrow() {
        try {
            return patientRepository.findAll()
                    .stream()
                    .map(patientDTOMapper)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new PatientServiceException(GET_ALL_PATIENTS_ERR_MSG.getMessage(), exception);
        }
    }
}
