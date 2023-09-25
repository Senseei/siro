package com.internacao.siro.validators.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.internacao.siro.dto.contactAttempt.NewSuccessContactAttemptDTO;
import com.internacao.siro.dto.contactAttempt.NewUnsuccessContactAttemptDTO;
import com.internacao.siro.repositories.EmployeeRepository;

@Component
public class ContactAttemptJson extends Json {

    @Autowired
    EmployeeRepository employeeRepository;

    public void validate(NewSuccessContactAttemptDTO body) {
        validateEntityField(body.getEmployeeId(), employeeRepository, "Employee");
        validateField(body.getPhoneNumber(), "Phone number");
        validateField(body.getAttemptTime(), "Attempt time");
        validateField(body.getPersonWhoAnswered(), "Person who answered");
        validateField(body.getRelationship(), "Relationship");
    }

    public void validate(NewUnsuccessContactAttemptDTO body) {
        validateEntityField(body.getEmployeeId(), employeeRepository, "Employee");
        validateField(body.getReasonForNotCalling(), "Reason for not calling");
        if (body.getPhoneNumber() != null)
            validateField(body.getAttemptTime(), "Attempt time");
    }
}
