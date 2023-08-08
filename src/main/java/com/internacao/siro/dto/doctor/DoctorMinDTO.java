package com.internacao.siro.dto.doctor;

import com.internacao.siro.dto.person.PersonMinDTO;
import com.internacao.siro.entities.Doctor;

public class DoctorMinDTO extends PersonMinDTO {
    
    private Long crm;

    public DoctorMinDTO() {}

    public DoctorMinDTO(Doctor doctor) {
        super(doctor);
        if (doctor != null) {
            crm = doctor.getCrm();
        }
    }

    public Long getCrm() {
        return crm;
    }
}