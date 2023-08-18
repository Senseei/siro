package com.internacao.siro.dto.patient;

import com.internacao.siro.dto.person.PersonDTO;
import com.internacao.siro.entities.Patient;

public class PatientDTO extends PersonDTO {

    private Long mr;

    public PatientDTO() {}

    public PatientDTO(Patient patient) {
        super(patient);
        mr = patient.getMr();
    }

    public static PatientDTO of(Patient patient) {
        if (patient == null)
            return null;
        return new PatientDTO(patient);
    }

    public Long getMr() {
        return mr;
    }
}
