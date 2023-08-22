INSERT INTO tb_people (name, birthday, PERSON_TYPE, crm) values ('Robert Patson', '2003-08-25', 'Doctor', 66007);

INSERT INTO tb_people (name, birthday, PERSON_TYPE) values ('João Siqueira', '1987-08-21', 'Person');

INSERT INTO tb_people (name, birthday, PERSON_TYPE, mr) values ('Jose Costa', '1984-05-10', 'Patient', 254302);

INSERT INTO tb_people (name, birthday, PERSON_TYPE, re) values ('Lucas Oliveira', '2000-02-17', 'Employee', 8607123);

/* RELATIVES */
INSERT INTO tb_relatives (relative_id, patient_id, relationship) values (2, 3, 'Brother');

/* CLINICS */
INSERT INTO tb_clinics (name) values ('P.S. Emergencia');

/* REGISTERS */
INSERT INTO tb_registers (patient_id, date_of_death, doctor_id, clinic_id, relative_id, documentation_withdrawal, employee_id) values (3, '2023-08-02 12:50:00', 1, 1, 2, '2023-08-02 16:50:00', 4);

/* DOCUMENTATION */
INSERT INTO tb_documentation (register_id, doc, doctor_id, canceled) values (1, 'IML/SVO', 1, 'FALSE');

/* OCCURRENCES */
INSERT INTO tb_occurrences (register_id, employee_id, description) values (1, 4, 'Test description');

/* CONTACT ATTEMPTS */