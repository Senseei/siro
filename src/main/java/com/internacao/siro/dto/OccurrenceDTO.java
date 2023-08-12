package com.internacao.siro.dto;

import com.internacao.siro.dto.employee.EmployeeMinDTO;
import com.internacao.siro.entities.Occurrence;

public class OccurrenceDTO {
    
    private Long id;
    private EmployeeMinDTO employee;
    private String description;

    public OccurrenceDTO() {}

    public OccurrenceDTO(Occurrence occurrence) {
        id = occurrence.getId();
        employee = new EmployeeMinDTO(occurrence.getEmployee());
        description = occurrence.getDescription();
    }
    
    public String getDescription() {
        return description;
    }

    public EmployeeMinDTO getEmployee() {
        return employee;
    }

    public Long getId() {
        return id;
    }
}
