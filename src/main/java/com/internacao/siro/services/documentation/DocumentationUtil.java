package com.internacao.siro.services.documentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.internacao.siro.dto.documentation.CancelDocumentationDTO;
import com.internacao.siro.dto.documentation.NewDocumentationDTO;
import com.internacao.siro.exception.InvalidJsonFormatException;
import com.internacao.siro.repositories.DoctorRepository;
import com.internacao.siro.repositories.PatientRepository;
import com.internacao.siro.repositories.RegisterRepository;
import com.internacao.siro.services.register.RegisterUtil;

@Component
public class DocumentationUtil extends RegisterUtil {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    DoctorRepository doctorRepository;

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
