package com.internacao.siro.dto;

import java.time.LocalDate;

import com.internacao.siro.entities.Doctor;
import com.internacao.siro.entities.Person;
import com.internacao.siro.projections.DoctorProjection;

public class DoctorDTO {
    
    private Long personId;
    private String name;
    private LocalDate birthday;
    private Long crm;

    public DoctorDTO() {}

    public DoctorDTO(Doctor doctor) {
        Person personInfo = doctor.getPerson();
        personId = personInfo.getId();
        name = personInfo.getName();
        birthday = personInfo.getBirthday();
        crm = doctor.getCrm();
    }

    public DoctorDTO(DoctorProjection projection) {
        if (projection != null) {
            personId = projection.getPersonId();
            name = projection.getName();
            birthday = projection.getBirthday();
            crm = projection.getCrm();
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

    public Long getCrm() {
        return crm;
    }
}
