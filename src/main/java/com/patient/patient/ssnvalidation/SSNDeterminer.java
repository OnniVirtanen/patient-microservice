package com.patient.patient.ssnvalidation;

public class SSNDeterminer {
    public static SSNType determineSSNType(final String ssn) {
        // Logic to determine if the SSN is in the new format
        if (isNewFormat(ssn)) {
            return SSNType.NEW;
        } else {
            return SSNType.OLD;
        }
    }

    private static boolean isNewFormat(final String ssn) {
        // Placeholder logic
        return true; // You would have actual logic here.
    }
}
