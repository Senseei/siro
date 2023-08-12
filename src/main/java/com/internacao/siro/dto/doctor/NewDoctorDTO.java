package com.internacao.siro.dto.doctor;

import java.time.LocalDate;

import com.internacao.siro.dto.person.NewPersonDTO;

public class NewDoctorDTO extends NewPersonDTO {
    
    private Long crm;

    public NewDoctorDTO() {}

    public NewDoctorDTO(String name, LocalDate birthday, String cpf, Long crm) {
        super(name, birthday, cpf);
        this.crm = crm;
    }

    public Long getCrm() {
        return crm;
    }

    public void setCrm(Long crm) {
        this.crm = crm;
    }
}
