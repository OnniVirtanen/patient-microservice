package com.patient.patient.domain.person.valueobject;

import com.patient.patient.domain.ValueObject;

import java.util.Objects;

public final class SocialSecurityNumber implements ValueObject {
    private final String SSN;

    public SocialSecurityNumber(String SSN) {
        validateSSN(SSN);

        this.SSN = SSN;
    }

    public String getSSN() {
        return SSN;
    }

    private static void validateSSN(String SSN) {
        if (SSN == null) {
            throw new NullPointerException("SSN cannot be null.");
        } else if (SSN.trim().isEmpty()) {
            throw new IllegalArgumentException("SSN cannot be empty.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialSecurityNumber that = (SocialSecurityNumber) o;
        return Objects.equals(SSN, that.SSN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SSN);
    }
}
