package com.patient.patient.model;

import com.patient.patient.persistence.PatientEntity;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Service
public class PatientDTOMapper implements Function<PatientEntity, PatientDTO>
{
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public PatientDTO apply(PatientEntity patientEntity)
    {
        return new PatientDTO(
            patientEntity.getId(),
            patientEntity.getGender(),
            patientEntity.getFirstName(),
            patientEntity.getSecondName(),
            patientEntity.getLastName(),
            patientEntity.getDateOfBirth()
        );
    }

    @Override
    public <V> Function<V, PatientDTO> compose(Function<? super V, ? extends PatientEntity> before)
    {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<PatientEntity, V> andThen(Function<? super PatientDTO, ? extends V> after)
    {
        return Function.super.andThen(after);
    }
}