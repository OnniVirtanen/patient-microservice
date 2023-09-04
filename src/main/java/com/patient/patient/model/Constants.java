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
}
