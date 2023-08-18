package com.internacao.siro.dto.employee;

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

    public static EmployeeMinDTO of(Employee employee) {
        if (employee == null)
            return null;
        return new EmployeeMinDTO(employee);
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
