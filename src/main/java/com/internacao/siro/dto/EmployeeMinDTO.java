package com.internacao.siro.dto;

import com.internacao.siro.entities.Employee;

public class EmployeeMinDTO {
    
    private String name;
    private Long re;

    public EmployeeMinDTO() {}

    public EmployeeMinDTO(Employee employee) {
        name = employee.getName();
        re = employee.getRe();
    }

    public String getName() {
        return name;
    }

    public Long getRe() {
        return re;
    }
}
