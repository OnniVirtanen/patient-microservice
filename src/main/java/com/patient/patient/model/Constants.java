package com.patient.patient.model;

/**
 * Provides constant values that are used throughout the application.
 */
public abstract class Constants {

    public static final String API_VERSION = "v1";
    public static final String PATIENT_CONTROLLER_API_PATH = "api/" + API_VERSION + "/patients";
    public static final String GET_ALL_PATIENTS_ERR_MSG = "Failure in getting all patients.";
    public static final String CREATE_PATIENT_ERR_MSG = "Failure in creating patient.";
    public static final String UPDATE_PATIENT_ERR_MSG = "Failure in updating patient.";
    public static final String DELETE_PATIENT_ERR_MSG = "Failure in deleting patient.";
    public static final String NO_PATIENT_FOUND_ERR_MSG = "No patient found with given id.";
    public static final String PATIENT_ALREADY_EXISTS_ERR_MSG = "Patient already exists.";
    public static final String PATIENT_REMOVE_SUCCESS_MSG = "Patient was removed successfully";
}
