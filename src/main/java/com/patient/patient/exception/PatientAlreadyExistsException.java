package com.patient.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PatientAlreadyExistsException extends RuntimeException {

    public PatientAlreadyExistsException(final String errorMessage) {
        super(errorMessage);
    }

    public PatientAlreadyExistsException(final String errorMessage, final Throwable cause) {
        super(errorMessage, cause);
    }

    public PatientAlreadyExistsException(final Throwable cause) {
        super(cause);
    }
}
