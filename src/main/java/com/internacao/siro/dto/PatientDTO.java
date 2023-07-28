package com.internacao.siro.dto;

import java.time.LocalDate;

import com.internacao.siro.entities.Patient;
import com.internacao.siro.entities.Person;
import com.internacao.siro.projections.PatientProjection;

public class PatientDTO {

    private Long personId;
    private String patientName;
    private LocalDate patientBirthday;
    private Long mr;

    public PatientDTO() {}

    public PatientDTO(Patient patient) {
        Person personInfo = patient.getPerson();
        personId = personInfo.getId();
        patientName = personInfo.getName();
        patientBirthday = personInfo.getBirthday();
        mr = patient.getMr();
    }

    public PatientDTO(PatientProjection patientProjection) {
        if (patientProjection != null) {
            personId = patientProjection.getPersonId();
            patientName = patientProjection.getPatientName();
            patientBirthday = patientProjection.getPatientBirthday();
            mr = patientProjection.getMr();
        }
    }
    
    public Long getPersonId() {
        return personId;
    }

    public String getPatientName() {
        return patientName;
    }

    public LocalDate getPatientBirthday() {
        return patientBirthday;
    }

    public Long getMr() {
        return mr;
    }
}
