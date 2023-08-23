package com.internacao.siro.services.occurrence;

import org.springframework.stereotype.Component;

import com.internacao.siro.dto.occurrence.NewOccurrenceDTO;
import com.internacao.siro.services.register.RegisterUtil;

@Component
public class OccurrenceUtil extends RegisterUtil {
    
    void validateJson(NewOccurrenceDTO body) {
        validateJsonEntityField(body.getEmployeeId(), employeeRepository, "Employee");
        validateJsonField(body.getDescription(), "Description");
    }
}
