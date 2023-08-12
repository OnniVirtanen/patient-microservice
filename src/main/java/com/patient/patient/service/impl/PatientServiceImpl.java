package com.patient.patient.service.impl;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.*;
import com.patient.patient.persistence.AddressEntity;
import com.patient.patient.persistence.AddressRepository;
import com.patient.patient.persistence.PatientEntity;
import com.patient.patient.persistence.PatientRepository;
import com.patient.patient.service.PatientService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);
    private final PatientRepository patientRepository;
    private final AddressRepository addressRepository;
    private final PatientDTOMapper patientDTOMapper;

    public PatientServiceImpl(PatientRepository patientRepository, AddressRepository addressRepository,
                              PatientDTOMapper patientDTOMapper) {
        this.patientRepository = patientRepository;
        this.addressRepository = addressRepository;
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
    public ResponseEntity<String> createPatient(final PatientEntity patient) throws PatientServiceException {
        try {
            patientRepository.save(patient);
            return ResponseEntity.ok("Patient created successfully.");
        } catch (Exception e) {
            throw new PatientServiceException("Failure in saving patient.", e);
        }
    }

    @Override
    public ResponseEntity<String> updatePatient(final NewPatientRequest patientRequest, final UUID id) throws PatientServiceException {
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
                    patientRepository.save(patient);
                    return ResponseEntity.ok("Patient updated successfully.");
                } catch (Exception e) {
                    throw new PatientServiceException("Failure in saving patient.", e);
                }
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            throw new PatientServiceException(e);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<String> removePatient(final UUID id) throws PatientServiceException {
        final Optional<PatientEntity> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            final PatientEntity patient = optionalPatient.get();
            // remove association between the patient and its addresses
            Set<AddressEntity> addresses = patient.getAddresses();
            for (AddressEntity address : addresses) {
                patient.removeAddress(address);
            }

            patientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
