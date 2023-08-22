package com.internacao.siro.services.documentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.internacao.siro.dto.documentation.CancelDocumentationDTO;
import com.internacao.siro.dto.documentation.NewDocumentationDTO;
import com.internacao.siro.entities.Patient;
import com.internacao.siro.entities.Register;
import com.internacao.siro.exception.InvalidJsonFormatException;
import com.internacao.siro.repositories.DoctorRepository;
import com.internacao.siro.repositories.PatientRepository;
import com.internacao.siro.repositories.RegisterRepository;
import com.internacao.siro.services.register.RegisterUtil;

import jakarta.persistence.EntityNotFoundException;

@Component
public class DocumentationUtil extends RegisterUtil {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    DoctorRepository doctorRepository;

    public Register checkForRegisterByMr(Long mr) {
        Patient patient = patientRepository.findByMr(mr);
        if (patient == null)
            throw new EntityNotFoundException("The patient with the given medical record does not exist");

        Register register = registerRepository.findByPatient(patient);
        if (register == null)
            throw new EntityNotFoundException("There is no register with this patient yet");

        return register;
    }

    public void validateJson(CancelDocumentationDTO body) {
        if (body.getCause() == null)
            throw new InvalidJsonFormatException("Cause cannot be null");
        validateJsonEntityField(body.getEmployeeId(), employeeRepository, "Employee");
    }

    public void validateJson(NewDocumentationDTO body) {
        if (body.getDoc() == null)
            throw new InvalidJsonFormatException("Doc number cannot be null");
        validateEntityExistence(body.getDoctorId(), doctorRepository, "Doctor");
    }
}
