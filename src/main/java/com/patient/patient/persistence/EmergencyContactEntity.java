package com.patient.patient.persistence;

import com.patient.patient.model.RelationshipEnum;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "relationship", nullable = false)
    private RelationshipEnum relationship;

    @Column(name = "social_security_number", nullable = false)
    private String SSN;

    @OneToMany(mappedBy = "emergencyContact", cascade = CascadeType.ALL)
    private List<AddressEntity> addresses;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", nullable = true)
    private PatientEntity patient;

    public EmergencyContactEntity(UUID id, String firstName, String secondName, String lastName, String phoneNumber,
                                  RelationshipEnum relationship, String SSN, List<AddressEntity> addresses,
                                  PatientEntity patient) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.relationship = relationship;
        this.SSN = SSN;
        this.addresses = addresses;
        this.patient = patient;
    }

    public EmergencyContactEntity() {

    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
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

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressEntity> addresses) {
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
                && Objects.equals(patient, that.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, lastName, phoneNumber, relationship, SSN, addresses, patient);
    }
}
