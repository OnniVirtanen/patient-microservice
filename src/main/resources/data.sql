-- Insert data into address
INSERT INTO public.address (primary_address, id, city, country, postal_code, state, street) VALUES
(true, '550e8400-e29b-41d4-a716-446655440000', 'San Francisco', 'USA', '94103', 'CA', '123 ABC Street'),
(true, '6ba7b810-9dad-11d1-80b4-00c04fd430c8', 'New York', 'USA', '10001', 'NY', '456 DEF Street');

-- Insert data into patient
INSERT INTO public.patient (date_of_birth, id, first_name, gender, last_name, second_name, social_security_number) VALUES
('1985-12-01', '6ba7b810-9dad-11d1-80b4-00c04fd430c9', 'John', 'MALE', 'Doe', 'Robert', '123-45-6789'),
('1990-05-05', '6ba7b810-9dad-11d1-80b4-00c04fd430c0', 'Jane', 'FEMALE', 'Doe', 'Rachel', '987-65-4321');

-- Insert data into patient_address
INSERT INTO public.patient_address (address_id, patient_id) VALUES
('550e8400-e29b-41d4-a716-446655440000', '6ba7b810-9dad-11d1-80b4-00c04fd430c9'),
('6ba7b810-9dad-11d1-80b4-00c04fd430c8', '6ba7b810-9dad-11d1-80b4-00c04fd430c0');

-- Insert data into emergency_contact
INSERT INTO public.emergency_contact (id, first_name, last_name, phone_number, relationship, second_name, social_security_number) VALUES
('6ba7b810-9dad-11d1-80b4-00c04fd430c1', 'Emily', 'Smith', '1234567890', 'SPOUSE', 'Rachel', '234-56-7890'),
('6ba7b810-9dad-11d1-80b4-00c04fd430c2', 'Michael', 'Johnson', '0987654321', 'PARENT', 'Robert', '876-54-3210');

-- Insert data into emergency_contact_address
INSERT INTO public.emergency_contact_address (address_id, emergency_contact_id) VALUES
('550e8400-e29b-41d4-a716-446655440000', '6ba7b810-9dad-11d1-80b4-00c04fd430c1'),
('6ba7b810-9dad-11d1-80b4-00c04fd430c8', '6ba7b810-9dad-11d1-80b4-00c04fd430c2');

-- Insert data into patient_emergency_contact
INSERT INTO public.patient_emergency_contact (emergency_contact_id, patient_id) VALUES
('6ba7b810-9dad-11d1-80b4-00c04fd430c1', '6ba7b810-9dad-11d1-80b4-00c04fd430c9'),
('6ba7b810-9dad-11d1-80b4-00c04fd430c2', '6ba7b810-9dad-11d1-80b4-00c04fd430c0');