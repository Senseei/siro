package com.internacao.siro.dto;

import java.time.LocalDate;

import com.internacao.siro.entities.Employee;
import com.internacao.siro.projections.EmployeeProjection;

public class EmployeeDTO {
    
    private Long id;
    private String name;
    private LocalDate birthday;
    private Long re;

    public EmployeeDTO() {}

    public EmployeeDTO(Employee employee) {
        if (employee != null) {
            id = employee.getId();
            name = employee.getName();
            birthday = employee.getBirthday();
            re = employee.getRe();
        }
        
    }

    public EmployeeDTO(EmployeeProjection projection) {
        if (projection != null) {
            id = projection.getId();
            name = projection.getName();
            birthday = projection.getBirthday();
            re = projection.getRe();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Long getRe() {
        return re;
    }
}
