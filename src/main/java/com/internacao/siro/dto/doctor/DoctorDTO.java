package com.internacao.siro.dto.doctor;

import com.internacao.siro.dto.person.PersonDTO;
import com.internacao.siro.entities.Doctor;

public class DoctorDTO extends PersonDTO {
    
    private Long crm;

    public DoctorDTO() {}

    public DoctorDTO(Doctor doctor) {
        super(doctor);
        if (doctor != null) {
            crm = doctor.getCrm();
        }
    }

    public Long getCrm() {
        return crm;
    }
}
