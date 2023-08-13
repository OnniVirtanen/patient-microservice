package com.patient.patient.persistence;

import com.patient.patient.model.GenderEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void itShouldCheckIfPatientExistsBySSN() {
        // given
        final String generatedSSN = generateSSN();
        
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Jane");
        patient.setLastName("Jaun");
        patient.setSecondName("Jun");
        patient.setSSN(generatedSSN);
        patient.setDateOfBirth(LocalDate.now());
        patient.setGender(GenderEnum.MALE);

        patientRepository.save(patient);

        // when
        boolean expected = patientRepository.existsBySSN(generatedSSN);

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckThatPatientDoesNotExistByMadeUpSSN() {
        // given
        final String generatedSSN = generateSSN();

        // when
        boolean expected = patientRepository.existsBySSN(generatedSSN);

        // then
        assertThat(expected).isFalse();
    }

    private static String generateSSN() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}