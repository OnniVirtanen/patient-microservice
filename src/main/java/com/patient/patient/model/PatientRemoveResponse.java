package com.patient.patient.model;

public class PatientRemoveResponse {

    private boolean patientWasFound;

    public boolean patientWasFound() {
        return patientWasFound;
    }

    public void setPatientWasFound(boolean patientWasFound) {
        this.patientWasFound = patientWasFound;
    }
}
