package com.patient.patient.model;

import com.patient.patient.persistence.PatientEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Service class responsible for mapping a PatientEntity to a PatientDTO.
 * It implements the Function interface to provide a transformation from PatientEntity to PatientDTO.
 */
@Service
public class PatientDTOMapper implements Function<PatientEntity, PatientDTO>
{

    /**
     * Converts a PatientEntity object into a PatientDTO object.
     *
     * @param patientEntity the patient entity to be converted.
     * @return a new PatientDTO object containing the data from the provided entity.
     */
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

    /**
     * Composes this function with the specified function to execute before this one.
     *
     * @param before the function to execute before this one.
     * @return a composed function that executes the before function followed by this function.
     */
    @Override
    public <V> Function<V, PatientDTO> compose(Function<? super V, ? extends PatientEntity> before)
    {
        return Function.super.compose(before);
    }

    /**
     * Chains this function with another function to execute after this one.
     *
     * @param after the function to execute after this one.
     * @return a chained function that executes this function followed by the after function.
     */
    @Override
    public <V> Function<PatientEntity, V> andThen(Function<? super PatientDTO, ? extends V> after)
    {
        return Function.super.andThen(after);
    }
}