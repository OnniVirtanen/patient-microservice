package com.patient.patient.domain.emergencycontact;

import com.patient.patient.domain.emergencycontact.valueobject.PhoneNumber;
import com.patient.patient.domain.emergencycontact.valueobject.Relationship;
import com.patient.patient.domain.person.Person;

public class EmergencyContact extends Person {

    private PhoneNumber phoneNumber;
    private Relationship relationshipToPerson;
}
