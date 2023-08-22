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
        employee = EmployeeMinDTO.of(occurrence.getEmployee());
        description = occurrence.getDescription();
    }

    public static OccurrenceDTO of(Occurrence occurrence) {
        if (occurrence == null)
            return null;
        return new OccurrenceDTO(occurrence);
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
