package com.patient.patient.controller;

import com.patient.patient.model.NewPatientRequest;
import com.patient.patient.model.PatientDTO;
import com.patient.patient.service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.patient.patient.model.Constants.PATIENT_CONTROLLER_API_PATH;
import static com.patient.patient.model.Constants.PATIENT_REMOVE_SUCCESS_MSG;

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
        PatientDTO savedPatient = patientService.createPatient(newPatient);
        return ResponseEntity.ok(savedPatient);
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping(path = "/{patientId}")
    public ResponseEntity<PatientDTO> getPatientById(final @PathVariable("patientId") UUID patientId) {
        PatientDTO patient = patientService.getPatientById(patientId);
        return ResponseEntity.ok(patient);
    }

    @PutMapping(path = "/{patientId}")
    public ResponseEntity<PatientDTO> updatePatient(final @PathVariable("patientId") UUID patientId,
                                                    final @Valid @RequestBody NewPatientRequest request) {
        PatientDTO updatedPatient = patientService.updatePatient(request, patientId);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping(path = "/{patientId}")
    public ResponseEntity<String> deletePatient(final @PathVariable("patientId") UUID patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.ok(PATIENT_REMOVE_SUCCESS_MSG);
    }
}