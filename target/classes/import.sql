INSERT INTO tb_people (name, birthday, PERSON_TYPE, crm) values ('Robert Patson', '2003-08-25', 'DOCTOR', 66007);

INSERT INTO tb_people (name, birthday, PERSON_TYPE) values ('João Siqueira', '1987-08-21', 'COMMON');

INSERT INTO tb_people (name, birthday, PERSON_TYPE, mr) values ('Jose Costa', '1984-05-10', 'PATIENT', 254302);

INSERT INTO tb_people (name, birthday, PERSON_TYPE, re) values ('Lucas Oliveira', '2000-02-17', 'EMPLOYEE', 8607123);

/* CLINICS */
INSERT INTO tb_clinics (name) values ('P.S. Emergencia');

/* REGISTERS */
INSERT INTO tb_registers (patient_id, date_of_death, doctor_id, clinic_id, documentation_withdrawal, employee_id) values (3, '2023-08-02 12:50:00', 1, 1, '2023-08-02 16:50:00', 4);

/* DOCUMENTATION */
INSERT INTO tb_documentation (register_id, doc) values (1, 'IML/SVO');

/* OCCURRENCES */
INSERT INTO tb_occurrences (register_id, employee_id, description) values (1, 4, 'Test description');

/* CONTACT ATTEMPTS */
INSERT INTO tb_contact_attempts (register_id, employee_id, phone_number, attempt_time, reason_for_not_calling) values (1, 4, '+5511912345678', '2023-08-02 13:00:00', 'Caixa postal');