package com.patient.patient.domain.person.valueobject;

import com.patient.patient.domain.ValueObject;

import java.util.Objects;

public final class Address implements ValueObject {
    private final String street;
    private final String city;
    private final String state;
    private final String postalCode;
    private final String country;

    public Address(String street, String city, String state, String postalCode, String country) {
        validateStreet(street);
        validateCity(city);
        validateState(state);
        validatePostalCode(postalCode);
        validateCountry(country);

        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    private static void validateStreet(String street) {
        if (street == null) {
            throw new NullPointerException("Street cannot be null.");
        } else if (street.trim().isEmpty()) {
            throw new IllegalArgumentException("Street cannot be empty.");
        }
    }

    private static void validateCity(String city) {
        if (city == null) {
            throw new NullPointerException("City cannot be null.");
        } else if (city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty.");
        } else if (containsOtherThanLetters(city)) {
            throw new IllegalArgumentException("City can only contain letters.");
        }
    }

    private static boolean containsOtherThanLetters(String text) {
        String textWithoutWhitespace = text.replaceAll("\\s", "");
        return !textWithoutWhitespace.chars().allMatch(Character::isLetter);
    }

    private static void validateState(String state) {
        if (state == null) {
            throw new NullPointerException("State cannot be null.");
        } else if (state.trim().isEmpty()) {
            throw new IllegalArgumentException("State cannot be empty.");
        }
    }

    private static void validatePostalCode(String postalCode) {
        if (postalCode == null) {
            throw new NullPointerException("PostalCode cannot be null.");
        } else if (postalCode.trim().isEmpty()) {
            throw new IllegalArgumentException("PostalCode cannot be empty.");
        }
    }

    private static void validateCountry(String country) {
        if (country == null) {
            throw new NullPointerException("Country cannot be null.");
        } else if (country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country cannot be empty.");
        } else if (containsOtherThanLetters(country)) {
            throw new IllegalArgumentException("Country can only contain letters.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(city, address.city) &&
                Objects.equals(state, address.state) && Objects.equals(postalCode, address.postalCode)
                && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, state, postalCode, country);
    }
}
