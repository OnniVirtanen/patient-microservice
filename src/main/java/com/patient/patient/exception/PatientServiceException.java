package com.patient.patient.exception;

/**
 * Custom exception class for handling patient service-specific exceptions.
 */
public class PatientServiceException extends Exception {

    /**
     * Constructs a new PatientServiceException with the specified error message.
     *
     * @param errorMessage the error message associated with the exception
     */
    public PatientServiceException(final String errorMessage)
    {
        super(errorMessage);
    }

    /**
     * Constructs a new PatientServiceException with the specified error message
     * and a cause.
     *
     * @param errorMessage the error message associated with the exception
     * @param err the cause of this exception (used for chaining exceptions)
     */
    public PatientServiceException(final String errorMessage, final Throwable err)
    {
        super(errorMessage, err);
    }

    /**
     * Constructs a new PatientServiceException using another exception.
     *
     * @param exception the original exception
     */
    public PatientServiceException(final Exception exception)
    {
        super(exception);
    }
}
