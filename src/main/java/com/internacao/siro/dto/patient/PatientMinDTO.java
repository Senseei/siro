package com.internacao.siro.dto.patient;

import com.internacao.siro.dto.person.PersonMinDTO;
import com.internacao.siro.entities.Patient;

public class PatientMinDTO extends PersonMinDTO {
    
    private Long mr;

    public PatientMinDTO() {}

    public PatientMinDTO(Patient patient) {
        super(patient);
        if (patient != null) {
            mr = patient.getMr();
        }
    }

    public Long getMr() {
        return mr;
    }
}
