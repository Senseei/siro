package com.internacao.siro.dto.employee;

import com.internacao.siro.dto.person.PersonDTO;
import com.internacao.siro.entities.Employee;

public class EmployeeDTO extends PersonDTO {
    
    private Long re;

    public EmployeeDTO() {}

    public EmployeeDTO(Employee employee) {
        super(employee);
        re = employee.getRe();
    }

    public static EmployeeDTO of(Employee employee) {
        if (employee == null)
            return null;
        return new EmployeeDTO(employee);
    }

    public Long getRe() {
        return re;
    }
}
