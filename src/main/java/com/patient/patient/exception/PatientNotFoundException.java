package com.patient.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(final String errorMessage) {
        super(errorMessage);
    }

    public PatientNotFoundException(final String errorMessage, final Throwable cause) {
        super(errorMessage, cause);
    }

    public PatientNotFoundException(final Throwable cause) {
        super(cause);
    }
}
