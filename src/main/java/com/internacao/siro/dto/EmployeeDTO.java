package com.internacao.siro.dto;

import com.internacao.siro.entities.Employee;

public class EmployeeDTO extends PersonDTO {
    
    private Long re;

    public EmployeeDTO() {}

    public EmployeeDTO(Employee employee) {
        super(employee);
        if (employee != null) {
            re = employee.getRe();
        }
        
    }

    public Long getRe() {
        return re;
    }
}
