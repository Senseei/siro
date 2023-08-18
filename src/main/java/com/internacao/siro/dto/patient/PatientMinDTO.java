package com.internacao.siro.dto.patient;

import com.internacao.siro.dto.person.PersonMinDTO;
import com.internacao.siro.entities.Patient;

public class PatientMinDTO extends PersonMinDTO {
    
    private Long mr;

    public PatientMinDTO() {}

    public PatientMinDTO(Patient patient) {
        super(patient);
        mr = patient.getMr();
    }

    public static PatientMinDTO of(Patient patient) {
        if (patient == null)
            return null;
        return new PatientMinDTO(patient);
    }

    public Long getMr() {
        return mr;
    }
}
