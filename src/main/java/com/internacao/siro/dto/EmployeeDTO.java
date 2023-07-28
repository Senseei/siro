package com.internacao.siro.dto;

import java.time.LocalDate;

import com.internacao.siro.entities.Employee;
import com.internacao.siro.entities.Person;
import com.internacao.siro.projections.EmployeeProjection;

public class EmployeeDTO {
    
    private Long personId;
    private String name;
    private LocalDate birthday;
    private Long re;

    public EmployeeDTO() {}

    public EmployeeDTO(Employee employee) {
        Person personInfo = employee.getPerson();
        personId = personInfo.getId();
        name = personInfo.getName();
        birthday = personInfo.getBirthday();
        re = employee.getRe();
    }

    public EmployeeDTO(EmployeeProjection projection) {
        if (projection != null) {
            personId = projection.getPersonId();
            name = projection.getName();
            birthday = projection.getBirthday();
            re = projection.getRe();
        }
    }

    public Long getPersonId() {
        return personId;
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
