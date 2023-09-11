package com.patient.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for handling patient service-specific exceptions.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class PatientServiceException extends RuntimeException {

    public PatientServiceException(final String errorMessage) {
        super(errorMessage);
    }

    public PatientServiceException(final String errorMessage, final Throwable err) {
        super(errorMessage, err);
    }

    public PatientServiceException(final Exception exception) {
        super(exception);
    }
}
