package com.internacao.siro.dto;

import com.internacao.siro.entities.Clinic;

public class ClinicDTO {
    
    private Long id;
    private String name;

    public ClinicDTO() {}

    public ClinicDTO(Clinic clinic) {
        id = clinic.getId();
        name = clinic.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
