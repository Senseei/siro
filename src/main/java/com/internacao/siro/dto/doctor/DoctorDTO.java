package com.internacao.siro.dto.doctor;

import com.internacao.siro.dto.person.PersonDTO;
import com.internacao.siro.entities.Doctor;

public class DoctorDTO extends PersonDTO {
    
    private Long crm;

    public DoctorDTO() {}

    public DoctorDTO(Doctor doctor) {
        super(doctor);
        crm = doctor.getCrm();
    }

    public static DoctorDTO of(Doctor doctor) {
        if (doctor == null)
            return null;
        return new DoctorDTO(doctor);
    }

    public Long getCrm() {
        return crm;
    }
}
