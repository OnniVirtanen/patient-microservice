-- Create patient table
CREATE TABLE patient (
    id UUID PRIMARY KEY,
    social_security_number VARCHAR(255) NOT NULL,
    gender VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    second_name VARCHAR(255),
    last_name VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL
);

-- Create emergency_contact table
CREATE TABLE emergency_contact (
    id UUID PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    second_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    relationship VARCHAR(255) NOT NULL,
    social_security_number VARCHAR(255) NOT NULL,
    patient_id UUID,
    FOREIGN KEY (patient_id) REFERENCES patient(id)
);

-- Create address table
CREATE TABLE address (
    id UUID PRIMARY KEY,
    country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    house_number VARCHAR(255) NOT NULL,
    postal_code VARCHAR(255) NOT NULL,
    patient_id UUID,
    emergency_contact_id UUID,
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (emergency_contact_id) REFERENCES emergency_contact(id)
);