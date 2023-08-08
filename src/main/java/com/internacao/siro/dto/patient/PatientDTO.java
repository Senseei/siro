package com.internacao.siro.dto.patient;

import com.internacao.siro.dto.person.PersonDTO;
import com.internacao.siro.entities.Patient;

public class PatientDTO extends PersonDTO {

    private Long mr;

    public PatientDTO() {}

    public PatientDTO(Patient patient) {
        super(patient);
        if (patient != null) {
            mr = patient.getMr();
        }
    }

    public Long getMr() {
        return mr;
    }
}
