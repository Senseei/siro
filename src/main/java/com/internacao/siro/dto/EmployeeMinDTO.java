package com.internacao.siro.dto;

import com.internacao.siro.entities.Employee;

public class EmployeeMinDTO {

    private Long id;
    private String name;
    private Long re;

    public EmployeeMinDTO() {}

    public EmployeeMinDTO(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        re = employee.getRe();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getRe() {
        return re;
    }
}
