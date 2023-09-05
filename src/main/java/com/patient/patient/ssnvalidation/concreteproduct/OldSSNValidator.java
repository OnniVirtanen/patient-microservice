package com.patient.patient.ssnvalidation.concreteproduct;

import com.patient.patient.ssnvalidation.SSNValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OldSSNValidator implements SSNValidator {
    @Override
    public boolean isValid(final String givenSocialSecurityNumber) {
        final Pattern PATTERN =
                Pattern.compile("^(0[1-9]|[1-2][0-9]|30|31)(0[1-9]|1[0-2])([0-9][0-9])[-+A][0-9]{3}[0-9A-Z]$");

        Matcher matcher = PATTERN.matcher(givenSocialSecurityNumber);

        StringBuilder SSN = new StringBuilder(givenSocialSecurityNumber);
        String birthDateAndNumberId = SSN.substring(0,6);
        birthDateAndNumberId += SSN.substring(7,10);

        if (birthDateAndNumberId.charAt(0) == '0')
            birthDateAndNumberId = birthDateAndNumberId.substring(1, birthDateAndNumberId.length() - 1);

        char[] ssnCheckMarkList = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'H', 'J',
                'K', 'L', 'M', 'N', 'P', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y'};

        Character ssnCheckMark = ssnCheckMarkList[Integer.parseInt(birthDateAndNumberId) % 31];
        Character userSsnCheckMark = givenSocialSecurityNumber.charAt(givenSocialSecurityNumber.length() - 1);
        boolean validSsnCheckMark = ssnCheckMark.equals(userSsnCheckMark);

        return matcher.matches() && validSsnCheckMark;
    }
}
