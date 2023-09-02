package com.patient.patient.controller;

import com.patient.patient.exception.PatientServiceException;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Rest Controller responsible for handling patient-related requests.
 */
@RestController
@RequestMapping("api/")
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Endpoint to create a new patient.
     *
     * @param request Patient creation request.
     * @return The newly created patient or an error response.
     */
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

    /**
     * Endpoint to retrieve a list of all patients.
     *
     * @return List of patients or an error response.
     */
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

    /**
     * Endpoint to update details of an existing patient.
     *
     * @param id      The UUID identifier of the patient to be updated.
     * @param request Patient update request.
     * @return The updated patient, a not found, or an error response.
     */
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

    /**
     * Endpoint to remove an existing patient.
     *
     * @param id The UUID identifier of the patient to be removed.
     * @return Success message or an error/not found response.
     */
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
