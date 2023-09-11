package com.patient.patient.model;

public enum ErrorMessage {

    GET_ALL_PATIENTS_ERR_MSG("Failure in fetching all patients."),
    CREATE_PATIENT_ERR_MSG("Failure in creating patient."),
    UPDATE_PATIENT_ERR_MSG("Failure in updating patient."),
    DELETE_PATIENT_ERR_MSG("Failure in deleting patient."),
    SAVE_PATIENT_ERR_MSG("Failure in saving patient."),
    NO_PATIENT_FOUND_ERR_MSG("No patient found with the given ID."),
    PATIENT_ALREADY_EXISTS_ERR_MSG("A patient with the given details already exists.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
