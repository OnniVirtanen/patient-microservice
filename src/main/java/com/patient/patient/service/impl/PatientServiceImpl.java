package com.patient.patient.service.impl;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.NewPatientRequest;
import com.patient.patient.model.PatientDTO;
import com.patient.patient.model.PatientDTOMapper;
import com.patient.patient.model.PatientDeleteResponse;
import com.patient.patient.persistence.PatientEntity;
import com.patient.patient.persistence.PatientRepository;
import com.patient.patient.service.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.patient.patient.model.Constants.GET_ALL_PATIENTS_ERR_MSG;
import static com.patient.patient.model.Constants.CREATE_PATIENT_ERR_MSG;
import static com.patient.patient.model.Constants.UPDATE_PATIENT_ERR_MSG;
import static com.patient.patient.model.Constants.DELETE_PATIENT_ERR_MSG;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientDTOMapper patientDTOMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientDTOMapper patientDTOMapper) {
        this.patientRepository = patientRepository;
        this.patientDTOMapper = patientDTOMapper;
    }

    @Override
    public List<PatientDTO> getAllPatients() throws PatientServiceException {
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
    public PatientDTO createPatient(final NewPatientRequest newPatient) throws PatientServiceException {
        try {
            PatientEntity savedPatient = patientRepository.save(new PatientEntity(newPatient));
            return patientDTOMapper.apply(savedPatient);
        } catch (Exception exception) {
            throw new PatientServiceException(CREATE_PATIENT_ERR_MSG, exception);
        }
    }

    @Override
    @Transactional
    public Optional<PatientDTO> updatePatient(final NewPatientRequest newPatientRequest, final UUID id)
            throws PatientServiceException {
        try {
            Optional<PatientEntity> patientFromDb = patientRepository.findById(id);
            if (patientFromDb.isEmpty()) { return Optional.empty(); }
            PatientEntity patient = PatientEntity.modifyPatient(newPatientRequest, patientFromDb.get());
            PatientEntity updatedPatient = patientRepository.save(patient);
            return Optional.of(patientDTOMapper.apply(updatedPatient));
        } catch (Exception exception) {
            throw new PatientServiceException(UPDATE_PATIENT_ERR_MSG, exception);
        }
    }

    @Override
    @Transactional
    public PatientDeleteResponse deletePatient(final UUID patientId) throws PatientServiceException {
        try {
            Optional<PatientEntity> patient = patientRepository.findById(patientId);
            boolean isPatientPresent = patient.isPresent();
            if (isPatientPresent) { patientRepository.deleteById(patientId); }
            return new PatientDeleteResponse(isPatientPresent);
        } catch (Exception exception) {
            throw new PatientServiceException(DELETE_PATIENT_ERR_MSG, exception);
        }
    }
}
