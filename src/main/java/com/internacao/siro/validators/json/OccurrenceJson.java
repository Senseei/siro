package com.internacao.siro.validators.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.internacao.siro.dto.occurrence.NewOccurrenceDTO;
import com.internacao.siro.repositories.EmployeeRepository;

@Component
public class OccurrenceJson extends Json {
    
    @Autowired
    EmployeeRepository employeeRepository;

    public void validate(NewOccurrenceDTO body) {
        validateEntityField(body.getEmployeeId(), employeeRepository, "Employee");
        validateField(body.getDescription(), "Description");
    }
}
