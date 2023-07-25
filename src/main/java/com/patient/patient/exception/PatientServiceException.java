package com.patient.patient.exception;

public class PatientServiceException extends Exception {
    public PatientServiceException(final String errorMessage)
    {
        super(errorMessage);
    }

    public PatientServiceException(final String errorMessage, final Throwable err)
    {
        super(errorMessage, err);
    }

    public PatientServiceException(final Exception exception)
    {
        super(exception);
    }
}
