package com.internacao.siro.validators.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.internacao.siro.dto.documentation.CancelDocumentationDTO;
import com.internacao.siro.dto.documentation.NewDocumentationDTO;
import com.internacao.siro.exception.InvalidJsonFormatException;
import com.internacao.siro.repositories.DoctorRepository;
import com.internacao.siro.repositories.EmployeeRepository;

@Component
public class DocumentationJson extends Json {
    
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DoctorRepository doctorRepository;

    public void validate(CancelDocumentationDTO body) {
        if (body.getCause() == null)
            throw new InvalidJsonFormatException("Cause cannot be null");
        validateEntityField(body.getEmployeeId(), employeeRepository, "Employee");
    }

    public void validate(NewDocumentationDTO body) {
        if (body.getDoc() == null)
            throw new InvalidJsonFormatException("Doc number cannot be null");
        validateEntityExistence(body.getDoctorId(), doctorRepository, "Doctor");
    }
}
