package com.patient.patient.persistence;

import com.patient.patient.model.RelationshipEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Represents an emergency contact entity within the system.
 * Holds details such as name, phone number, relationship to the patient, and associated addresses.
 */
@Entity(name = "Emergency_Contact")
@Table(name = "emergency_contact")
public class EmergencyContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    /**
     * Relationship of the emergency contact to the patient.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "relationship", nullable = false)
    private RelationshipEnum relationship;

    @Column(name = "social_security_number", nullable = false)
    private String SSN;

    /**
     * Collection of addresses associated with the emergency contact.
     */
    @ManyToMany
    @JoinTable(
            name = "emergency_contact_address",
            joinColumns = @JoinColumn(name = "emergency_contact_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<AddressEntity> addresses;

    /**
     * Collection of patients who have listed this entity as an emergency contact.
     */
    @ManyToMany(mappedBy = "emergencyContactEntities")
    private Set<PatientEntity> patients;

    public EmergencyContactEntity(UUID id, String firstName, String secondName, String lastName, String phoneNumber,
                                  RelationshipEnum relationship, String SSN, Set<AddressEntity> addresses,
                                  Set<PatientEntity> patients) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.relationship = relationship;
        this.SSN = SSN;
        this.addresses = addresses;
        this.patients = patients;
    }

    public EmergencyContactEntity() {

    }

    public Set<PatientEntity> getPatients() {
        return patients;
    }

    public void setPatients(Set<PatientEntity> patients) {
        this.patients = patients;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RelationshipEnum getRelationship() {
        return relationship;
    }

    public void setRelationship(RelationshipEnum relationship) {
        this.relationship = relationship;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public Set<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressEntity> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmergencyContactEntity that = (EmergencyContactEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName)
                && Objects.equals(secondName, that.secondName) && Objects.equals(lastName, that.lastName)
                && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(relationship, that.relationship)
                && Objects.equals(SSN, that.SSN) && Objects.equals(addresses, that.addresses)
                && Objects.equals(patients, that.patients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, lastName, phoneNumber, relationship, SSN, addresses, patients);
    }
}
