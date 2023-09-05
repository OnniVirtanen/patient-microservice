package com.patient.patient.ssnvalidation;

import com.patient.patient.ssnvalidation.concreteproduct.NewSSNValidator;
import com.patient.patient.ssnvalidation.concreteproduct.OldSSNValidator;

import static com.patient.patient.model.Constants.UNSUPPORTED_SSN_TYPE;

public class SSNValidatorFactory {
    public static SSNValidator createValidator(final String ssn) {
        SSNType type = SSNDeterminer.determineSSNType(ssn);
        return switch (type) {
            case NEW -> new NewSSNValidator();
            case OLD -> new OldSSNValidator();
            default -> throw new IllegalArgumentException(UNSUPPORTED_SSN_TYPE);
        };
    }
}
