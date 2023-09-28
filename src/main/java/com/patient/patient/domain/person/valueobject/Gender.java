package com.patient.patient.domain.person.valueobject;

import java.util.Objects;

public final class Gender {
    private final Type gender;

    public Gender(Type gender) {
        validateGender(gender);

        this.gender = gender;
    }

    private static void validateGender(Type gender) {
        if (gender == null) {
            throw new NullPointerException("Gender cannot be null.");
        }
    }

    public enum Type {
        MALE,
        FEMALE,
        OTHER,
        RATHER_NOT_SAY
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gender gender1 = (Gender) o;
        return gender == gender1.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender);
    }
}
