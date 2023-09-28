package com.patient.patient.domain.emergencycontact.valueobject;

import com.patient.patient.domain.ValueObject;

import java.util.Objects;

public final class PhoneNumber implements ValueObject {
    private final String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        validatePhoneNumber(phoneNumber);

        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            throw new NullPointerException("PhoneNumber cannot be null.");
        } else if (phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("PhoneNumber cannot be empty.");
        } else if (containsOtherThanDigits(phoneNumber)) {
            throw new IllegalArgumentException("PhoneNumber can only contain digits.");
        }
    }

    private static boolean containsOtherThanDigits(String phoneNumber) {
        return !phoneNumber.chars().allMatch(Character::isDigit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }
}
