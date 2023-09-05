package com.patient.patient.model;

/**
 * Represents the response after attempting to remove a patient from the system.
 * This class encapsulates information about whether the patient was found and removed successfully.
 */
public class PatientDeleteResponse {

    public PatientDeleteResponse(final boolean isPatientFound) {
        this.isPatientFound = isPatientFound;
    }

    private boolean isPatientFound;

    public boolean isPatientFound() {
        return isPatientFound;
    }

    public void setIsPatientFound(boolean isPatientFound) {
        this.isPatientFound = isPatientFound;
    }
}
