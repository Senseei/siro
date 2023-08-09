package com.internacao.siro.dto.doctor;

import com.internacao.siro.entities.Doctor;

public class DoctorMinDTO {
    
    private Long id;
    private String name;
    private Long crm;

    public DoctorMinDTO() {}

    public DoctorMinDTO(Doctor doctor) {
        if (doctor != null) {
            id = doctor.getId();
            name = doctor.getName();
            crm = doctor.getCrm();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getCrm() {
        return crm;
    }
}