package com.patient.patient.domain.person.valueobject;

import com.patient.patient.domain.ValueObject;

import java.util.Objects;

public final class FirstName implements ValueObject {
    private final String name;

    public FirstName(String name) {
        validateName(name);

        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static void validateName(String name) {
        if (name == null) {
            throw new NullPointerException("Name cananot be null.");
        } else if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstName firstName = (FirstName) o;
        return Objects.equals(name, firstName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
