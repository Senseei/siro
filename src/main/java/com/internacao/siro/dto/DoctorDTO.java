package com.internacao.siro.dto;

import java.time.LocalDate;

import com.internacao.siro.entities.Doctor;
import com.internacao.siro.projections.DoctorProjection;

public class DoctorDTO {
    
    private Long id;
    private String name;
    private LocalDate birthday;
    private Long crm;

    public DoctorDTO() {}

    public DoctorDTO(Doctor doctor) {
        if (doctor != null) {
            id = doctor.getId();
            name = doctor.getName();
            birthday = doctor.getBirthday();
            crm = doctor.getCrm();
        }
    }

    public DoctorDTO(DoctorProjection projection) {
        if (projection != null) {
            id = projection.getId();
            name = projection.getName();
            birthday = projection.getBirthday();
            crm = projection.getCrm();
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

    public Long getCrm() {
        return crm;
    }
}
