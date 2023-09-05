package com.patient.patient.controller;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.NewPatientRequest;
import com.patient.patient.model.PatientDTO;
import com.patient.patient.model.PatientDeleteResponse;
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

import static com.patient.patient.model.Constants.PATIENT_CONTROLLER_API_PATH;

/**
 * Rest Controller responsible for handling patient-related requests.
 */
@RestController
@RequestMapping(value = PATIENT_CONTROLLER_API_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(final @Validated @RequestBody NewPatientRequest newPatient) {
        try {
            PatientDTO savedPatient = patientService.createPatient(newPatient);
            return ResponseEntity.ok(savedPatient);
        } catch (PatientServiceException exception) {
            logger.error(exception.getMessage(), exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        try {
            List<PatientDTO> patientDTOS = patientService.getAllPatients();
            return ResponseEntity.ok(patientDTOS);
        } catch (PatientServiceException exception) {
            logger.error(exception.getMessage(), exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = "/{patientID}")
    public ResponseEntity<PatientDTO> updatePatient(final @PathVariable("patientID") UUID id,
                                                    final @Valid @RequestBody NewPatientRequest request) {
        try {
            Optional<PatientDTO> optionalPatient = patientService.updatePatient(request, id);
            return optionalPatient.map(ResponseEntity::ok).orElseGet(() ->
                    ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (PatientServiceException exception) {
            logger.error(exception.getMessage(), exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "/{patientID}")
    public ResponseEntity<String> deletePatient(final @PathVariable("patientID") UUID patientId) {
        try {
            PatientDeleteResponse deleteResponse = patientService.deletePatient(patientId);

            if (deleteResponse.isPatientFound()) {
                return ResponseEntity.ok("Patient was removed successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (PatientServiceException exception) {
            logger.error(exception.getMessage(), exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
