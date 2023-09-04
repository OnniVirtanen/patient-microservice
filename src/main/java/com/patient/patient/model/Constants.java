package com.patient.patient.model;

/**
 * Provides constant values that are used throughout the application.
 */
public abstract class Constants {

    /**
     * The current version of the API.
     */
    public static final String API_VERSION = "v1";

    /**
     * The base API path for accessing patient-related endpoints.
     */
    public static final String PATIENT_CONTROLLER_API_PATH = "api/" + API_VERSION + "/patients";

    /**
     *  Error messages for patient service
     */
    public static final String SELECT_PATIENTS_ERR_MSG = "Failure in selecting patients.";
    public static final String CREATE_PATIENT_ERR_MSG = "Failure in creating patient.";
    public static final String UPDATE_PATIENT_ERR_MSG = "Failure in updating patient.";
    public static final String REMOVE_PATIENT_ERR_MSG = "Failure in removing patient.";
}
