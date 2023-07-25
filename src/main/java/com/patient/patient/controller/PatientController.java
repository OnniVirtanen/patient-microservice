package com.patient.patient.controller;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.PatientDTO;
import com.patient.patient.serializer.PatientJsonConverter;
import com.patient.patient.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/")
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;
    private final PatientJsonConverter patientJsonConverter;

    public PatientController(PatientService patientService, PatientJsonConverter patientJsonConverter) {
        this.patientService = patientService;
        this.patientJsonConverter = patientJsonConverter;
    }

    @GetMapping(path = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> selectPatients() {
        try {
            final List<PatientDTO> patientDTOS = patientService.selectPatients();
            final String jsonString = patientJsonConverter.serialize(patientDTOS);
            return ResponseEntity.ok(jsonString);
        } catch (final PatientServiceException e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
