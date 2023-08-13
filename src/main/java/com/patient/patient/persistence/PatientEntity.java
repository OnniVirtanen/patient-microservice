package com.patient.patient.persistence;

import com.patient.patient.model.GenderEnum;
import com.patient.patient.model.NewPatientRequest;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Patient")
@Table(name = "patient")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "social_security_number", nullable = false)
    private String SSN;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private GenderEnum gender;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = true)
    private String secondName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @ManyToMany
    @JoinTable(
            name = "patient_address",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<AddressEntity> addresses;

    @ManyToMany
    @JoinTable(
            name = "patient_emergency_contact",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "emergency_contact_id"))
    private Set<EmergencyContactEntity> emergencyContactEntities;

    public PatientEntity(UUID id, String SSN, GenderEnum gender, String firstName, String secondName,
                         String lastName, LocalDate dateOfBirth, Set<AddressEntity> addresses,
                         Set<EmergencyContactEntity> emergencyContactEntities) {
        this.id = id;
        this.SSN = SSN;
        this.gender = gender;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.addresses = addresses;
        this.emergencyContactEntities = emergencyContactEntities;
    }

    public PatientEntity(NewPatientRequest request) {
        this.setFirstName(request.firstName());
        this.setSecondName(request.secondName());
        this.setLastName(request.lastName());
        this.setGender(request.gender());
        this.setSSN(request.SSN());
        this.setDateOfBirth(request.dateOfBirth());
    }

    public PatientEntity() {}

    public void removeAddress(AddressEntity address) {
        this.addresses.remove(address);
        address.getPatients().remove(this);
    }

    public Set<EmergencyContactEntity> getEmergencyContactEntities() {
        return emergencyContactEntities;
    }

    public void setEmergencyContactEntities(Set<EmergencyContactEntity> emergencyContactEntities) {
        this.emergencyContactEntities = emergencyContactEntities;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
        PatientEntity that = (PatientEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(SSN, that.SSN) && Objects.equals(gender, that.gender)
                && Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName)
                && Objects.equals(lastName, that.lastName) && Objects.equals(dateOfBirth, that.dateOfBirth)
                && Objects.equals(addresses, that.addresses)
                && Objects.equals(emergencyContactEntities, that.emergencyContactEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, SSN, gender, firstName, secondName, lastName, dateOfBirth, addresses,
                emergencyContactEntities);
    }
}
