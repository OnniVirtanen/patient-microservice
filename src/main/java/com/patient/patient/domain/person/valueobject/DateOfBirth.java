package com.patient.patient.domain.person.valueobject;

import com.patient.patient.domain.ValueObject;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class DateOfBirth implements ValueObject {
    private final LocalDate date;

    public DateOfBirth(LocalDate date) {
        validateDateOfBirth(date);
        
        this.date = date;
    }
    
    private static void validateDateOfBirth(LocalDate date) {
        if (date == null) {
            throw new NullPointerException("Date of birth cannot be null.");
        } else if (isInFuture(date)) {
            throw new IllegalArgumentException("Date of birth cannot be in future.");
        } else if (isOver150YearsAgo(date)) {
            throw new IllegalArgumentException("Date of birth cannot be 150 years ago.");
        }
    }

    private static boolean isInFuture(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    private static boolean isOver150YearsAgo(LocalDate date) {
        return Period.between(date, LocalDate.now()).getYears() > 150;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateOfBirth that = (DateOfBirth) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
