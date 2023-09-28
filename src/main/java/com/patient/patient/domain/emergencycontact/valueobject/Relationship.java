package com.patient.patient.domain.emergencycontact.valueobject;

import com.patient.patient.domain.ValueObject;

import java.util.Objects;

public final class Relationship implements ValueObject {
    private final Type relationship;

    public Relationship(Type relationship) {
        validateRelationship(relationship);

        this.relationship = relationship;
    }

    private static void validateRelationship(Type relationship) {
        if (relationship == null) {
            throw new NullPointerException("Relationship cannot be null.");
        }
    }

    public Type getRelationship() {
        return relationship;
    }

    public enum Type {
        PARENT,
        CHILD,
        SIBLING,
        SPOUSE,
        PARTNER,
        FRIEND,
        OTHER
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relationship that = (Relationship) o;
        return relationship == that.relationship;
    }

    @Override
    public int hashCode() {
        return Objects.hash(relationship);
    }
}
