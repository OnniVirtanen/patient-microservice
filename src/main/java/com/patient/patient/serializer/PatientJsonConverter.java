package com.patient.patient.serializer;

import com.patient.patient.model.PatientDTO;
import com.patient.patient.persistence.PatientEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientJsonConverter extends AbstractJsonConverter {
    public String serialize(final PatientEntity patientEntity)
    {
        return super.serialize(patientEntity);
    }

    public String serialize(final PatientDTO patientDTO)
    {
        return super.serialize(patientDTO);
    }

    public String serialize(final List<PatientDTO> patientDTOS)
    {
        return super.serialize(patientDTOS);
    }
}
