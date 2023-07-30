package com.patient.patient.controller;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.NewPatientRequest;
import com.patient.patient.model.PatientDTO;
import com.patient.patient.model.Response;
import com.patient.patient.persistence.PatientEntity;
import com.patient.patient.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> createPatient(final @RequestBody NewPatientRequest request) {
        try {
            final PatientEntity patient = new PatientEntity(request);
            patientService.createPatient(patient);
            return ResponseEntity.ok("Patient created successfully.");
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

    // update
    @PutMapping(path = "/patient/{patientID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePatient(final @PathVariable("patientID") UUID id,
                                                final @RequestBody NewPatientRequest request) {
        try {
            final Response response = patientService.updatePatient(request, id);

            if (response.getIsSuccessful()) {
                return ResponseEntity.ok(response.getMessage());
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(response.getMessage());
            }
        } catch (final PatientServiceException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // delete
}
