package com.internacao.siro.dto.clinic;

import com.internacao.siro.entities.Clinic;

public class ClinicDTO {
    
    private Long id;
    private String name;

    public ClinicDTO() {}

    public ClinicDTO(Clinic clinic) {
        id = clinic.getId();
        name = clinic.getName();
    }

    public static ClinicDTO of(Clinic clinic) {
        if (clinic == null)
            return null;
        return new ClinicDTO(clinic);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
