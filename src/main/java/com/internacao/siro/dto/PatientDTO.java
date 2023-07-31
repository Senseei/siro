package com.internacao.siro.dto;

import java.time.LocalDate;

import com.internacao.siro.entities.Patient;
import com.internacao.siro.projections.PatientProjection;

public class PatientDTO {

    private Long id;
    private String name;
    private LocalDate birthday;
    private Long mr;

    public PatientDTO() {}

    public PatientDTO(Patient patient) {
        if (patient != null) {
            id = patient.getId();
            name = patient.getName();
            birthday = patient.getBirthday();
            mr = patient.getMr();
        }
    }

    public PatientDTO(PatientProjection projection) {
        
        if (projection != null) {
            id = projection.getId();
            name = projection.getName();
            birthday = projection.getBirthday();
            mr = projection.getMr();
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

    public Long getMr() {
        return mr;
    }
}
