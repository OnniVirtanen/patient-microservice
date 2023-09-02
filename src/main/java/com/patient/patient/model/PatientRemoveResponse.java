package com.patient.patient.model;

/**
 * Represents the response after attempting to remove a patient from the system.
 * This class encapsulates information about whether the patient was found and removed successfully.
 */
public class PatientRemoveResponse {

    private boolean patientWasFound;

    public boolean patientWasFound() {
        return patientWasFound;
    }

    public void setPatientWasFound(boolean patientWasFound) {
        this.patientWasFound = patientWasFound;
    }
}
