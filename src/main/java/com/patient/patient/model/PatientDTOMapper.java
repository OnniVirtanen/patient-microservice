package com.patient.patient.model;

import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Service class responsible for mapping a PatientEntity to a PatientDTO.
 * It implements the Function interface to provide a transformation from PatientEntity to PatientDTO.
 */
@Service
public class PatientDTOMapper implements Function<PatientEntity, PatientDTO>
{

    @Override
    public PatientDTO apply(PatientEntity patientEntity)
    {
        return new PatientDTO(
            patientEntity.getId(),
            patientEntity.getGender(),
            patientEntity.getFirstName(),
            patientEntity.getSecondName(),
            patientEntity.getLastName(),
            patientEntity.getDateOfBirth(),
            patientEntity.getSSN()
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