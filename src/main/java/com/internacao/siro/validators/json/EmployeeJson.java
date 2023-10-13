package com.internacao.siro.validators.json;

import org.springframework.stereotype.Component;

import com.internacao.siro.dto.employee.NewEmployeeDTO;
import com.internacao.siro.dto.employee.UpdateEmployeeDTO;
import com.internacao.siro.exceptions.NegativeReNumberException;

@Component
public class EmployeeJson extends PersonJson {

    public void validate(NewEmployeeDTO body) {
        super.validate(body);
        validateRe(body.getRe(), false);
    }

    public void validate(UpdateEmployeeDTO body) {
        super.validate(body);
        validateRe(body.getRe(), true);
    }

    void validateRe(Long re, boolean nullable) {
        if (!nullable)
            validateField(re, "Registro do Empregado (RE)");
        if (re != null && re < 0)
            throw new NegativeReNumberException();
    }
}
