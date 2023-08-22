package com.internacao.siro.services.contacctAttempt;

import org.springframework.stereotype.Component;

import com.internacao.siro.dto.contactAttempt.NewSuccessContactAttemptDTO;
import com.internacao.siro.dto.contactAttempt.NewUnsuccessContactAttemptDTO;
import com.internacao.siro.services.register.RegisterUtil;

@Component
public class ContactAttemptUtil extends RegisterUtil {

    void validateJson(NewSuccessContactAttemptDTO body) {
        validateJsonEntityField(body.getEmployeeId(), employeeRepository, "Employee");
        validateJsonField(body.getPhoneNumber(), "Phone number");
        validateJsonField(body.getAttemptTime(), "Attempt time");
        validateJsonField(body.getPersonWhoAnswered(), "Person who answered");
        validateJsonField(body.getRelationship(), "Relationship");
    }

    void validateJson(NewUnsuccessContactAttemptDTO body) {
        validateJsonEntityField(body.getEmployeeId(), employeeRepository, "Employee");
        validateJsonField(body.getReasonForNotCalling(), "Reason for not calling");
        if (body.getPhoneNumber() != null)
            validateJsonField(body.getAttemptTime(), "Attempt time");
    }
}
