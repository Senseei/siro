package com.internacao.siro.dto.employee;

import com.internacao.siro.dto.person.PersonMinDTO;
import com.internacao.siro.entities.Employee;

public class EmployeeMinDTO extends PersonMinDTO {
    
    private Long re;

    public EmployeeMinDTO() {}

    public EmployeeMinDTO(Employee employee) {
        super(employee);
        if (employee != null) {
            re = employee.getRe();
        }
        
    }

    public Long getRe() {
        return re;
    }
}
