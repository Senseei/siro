package com.internacao.siro.validators.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.internacao.siro.dto.register.NewRegisterDTO;
import com.internacao.siro.dto.register.UpdateRegisterDTO;
import com.internacao.siro.repositories.ClinicRepository;
import com.internacao.siro.repositories.DoctorRepository;
import com.internacao.siro.repositories.EmployeeRepository;
import com.internacao.siro.repositories.PatientRepository;
import com.internacao.siro.repositories.PersonRepository;

@Component
public class RegisterJson extends Json {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PersonRepository personRepository;
    
    public void validate(UpdateRegisterDTO body) {

        validateEntityExistence(body.getPatientId(), patientRepository, "patient");
        validateEntityExistence(body.getDoctorId(), doctorRepository, "doctor");
        validateEntityExistence(body.getClinicId(), clinicRepository, "clinic");
        validateEntityExistence(body.getEmployeeId(), employeeRepository, "employee");
        if (body.getRelative() != null)
            validateEntityExistence(body.getRelative().getId(), personRepository, "relative");
    }

    public void validate(NewRegisterDTO body) {
        validateEntityField(body.getClinicId(), clinicRepository, "Clinic");
        validateEntityField(body.getDoctorId(), doctorRepository, "Doctor");
        validateEntityField(body.getPatientId(), patientRepository, "Patient");
    }
}
