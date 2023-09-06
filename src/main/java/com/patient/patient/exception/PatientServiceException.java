package com.patient.patient.exception;

/**
 * Custom exception class for handling patient service-specific exceptions.
 */
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
