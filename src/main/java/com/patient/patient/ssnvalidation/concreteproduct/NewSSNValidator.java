package com.patient.patient.ssnvalidation.concreteproduct;

import com.patient.patient.ssnvalidation.SSNValidator;

public class NewSSNValidator implements SSNValidator {
    @Override
    public boolean isValid(String ssn) {
        throw new UnsupportedOperationException("New SSN validator is not implemented.");
    }
}
