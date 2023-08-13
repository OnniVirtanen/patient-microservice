package com.patient.patient.service.impl;

import com.patient.patient.exception.PatientServiceException;
import com.patient.patient.model.GenderEnum;
import com.patient.patient.model.NewPatientRequest;
import com.patient.patient.model.PatientDTOMapper;
import com.patient.patient.persistence.AddressEntity;
import com.patient.patient.persistence.EmergencyContactEntity;
import com.patient.patient.persistence.PatientEntity;
import com.patient.patient.persistence.PatientRepository;
import com.patient.patient.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;
    @Mock
    private PatientDTOMapper patientDTOMapper;
    private PatientService patientService;


    @BeforeEach
    void setUp() {
        patientService = new PatientServiceImpl(patientRepository, patientDTOMapper);
    }

    @Test
    void canGetAllPatients() throws PatientServiceException {
        // when
        patientService.selectPatients();
        // then
        verify(patientRepository).findAll();
    }

    @Test
    void canCreatePatient() throws PatientServiceException {
        // given
        final PatientEntity patient = generatePatient();

        // when
        patientService.createPatient(patient);

        // then
        final ArgumentCaptor<PatientEntity> patientArgumentCaptor =
                ArgumentCaptor.forClass(PatientEntity.class);

        verify(patientRepository).save(patientArgumentCaptor.capture());

        final PatientEntity capturedPatient = patientArgumentCaptor.getValue();

        assertThat(capturedPatient).isEqualTo(patient);
    }

    @Test
    void canUpdatePatient() throws PatientServiceException {
        // given
        UUID patientId = UUID.randomUUID();
        NewPatientRequest patientRequest = generatePatientRequest();
        PatientEntity existingPatient = new PatientEntity();
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(existingPatient));

        // when
        patientService.updatePatient(patientRequest, patientId);

        // then
        verify(patientRepository).save(existingPatient);
    }

    @Test
    void canRemovePatient() throws PatientServiceException {
        // given
        UUID patientId = UUID.randomUUID();
        PatientEntity patient = new PatientEntity();
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));

        // when
        patientService.removePatient(patientId);

        // then
        verify(patientRepository).deleteById(patientId);
    }

    private static PatientEntity generatePatient() {
        return new PatientEntity(
                UUID.randomUUID(),
                "asd-123",
                GenderEnum.MALE,
                "firstName",
                "secondName",
                "lastName",
                LocalDate.now(),
                new HashSet<AddressEntity>(),
                new HashSet<EmergencyContactEntity>()
        );
    }

    private static NewPatientRequest generatePatientRequest() {
        return new NewPatientRequest(
            "firstName",
            "secondName",
            "lastName",
                GenderEnum.FEMALE,
                "asd-123",
                LocalDate.now()
        );
    }
}