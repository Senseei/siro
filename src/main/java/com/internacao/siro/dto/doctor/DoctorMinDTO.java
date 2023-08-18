package com.internacao.siro.dto.doctor;

import com.internacao.siro.entities.Doctor;

public class DoctorMinDTO {
    
    private Long id;
    private String name;
    private Long crm;

    public DoctorMinDTO() {}

    public DoctorMinDTO(Doctor doctor) {
        id = doctor.getId();
        name = doctor.getName();
        crm = doctor.getCrm();
    }

    public static DoctorMinDTO of(Doctor doctor) {
        if (doctor == null)
            return null;
        return new DoctorMinDTO(doctor);
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