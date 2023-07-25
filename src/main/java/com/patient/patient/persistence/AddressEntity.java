package com.patient.patient.persistence;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "address")
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "primary_address", nullable = false)
    private Boolean primaryAddress;

    @ManyToOne
    @JoinColumn(name = "emergency_contact_id", nullable = true)
    private EmergencyContactEntity emergencyContact;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", nullable = true)
    private PatientEntity patient;

    public AddressEntity(UUID id, String street, String city, String state, String postalCode, String country,
                         Boolean primaryAddress, EmergencyContactEntity emergencyContact, PatientEntity patient) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.primaryAddress = primaryAddress;
        this.emergencyContact = emergencyContact;
        this.patient = patient;
    }

    public AddressEntity() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Boolean primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public EmergencyContactEntity getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContactEntity emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(street, that.street) && Objects.equals(city, that.city)
                && Objects.equals(state, that.state) && Objects.equals(postalCode, that.postalCode)
                && Objects.equals(country, that.country) && Objects.equals(primaryAddress, that.primaryAddress)
                && Objects.equals(emergencyContact, that.emergencyContact) && Objects.equals(patient, that.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, state, postalCode, country, primaryAddress, emergencyContact, patient);
    }
}
