package com.patient.patient.controller;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.GenderEnum;
import com.patient.patient.model.NewPatientRequest;
import com.patient.patient.model.PatientDTO;
import com.patient.patient.model.PatientRemoveResponse;
import com.patient.patient.persistence.PatientEntity;
import com.patient.patient.service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/")
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(path = "/patient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> createPatient(final @Validated @RequestBody NewPatientRequest request) {
        try {
            final PatientDTO savedPatient = patientService.createPatient(new PatientEntity(request));
            return ResponseEntity.ok(savedPatient);
        } catch (final PatientServiceException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientDTO>> selectPatients() {
        try {
            final List<PatientDTO> patientDTOS = patientService.selectPatients();
            return ResponseEntity.ok(patientDTOS);
        } catch (final PatientServiceException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = "/patient/{patientID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> updatePatient(final @PathVariable("patientID") UUID id,
                                                final @Valid @RequestBody NewPatientRequest request) {
        try {
            final Optional<PatientDTO> optionalPatient = patientService.updatePatient(request, id);
            return optionalPatient.map(ResponseEntity::ok).orElseGet(() ->
                    ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (final PatientServiceException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "/patient/{patientID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removePatient(final @PathVariable("patientID") UUID id) {
        try {
            final PatientRemoveResponse removeResponse = patientService.removePatient(id);

            if (removeResponse.patientWasFound()) {
                return ResponseEntity.ok("Patient was removed successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (final PatientServiceException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
