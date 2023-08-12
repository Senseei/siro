package com.internacao.siro.dto.patient;

import java.time.LocalDate;

import com.internacao.siro.dto.person.NewPersonDTO;

public class NewPatientDTO extends NewPersonDTO {
    
    private Long mr;

    public NewPatientDTO() {}

    public NewPatientDTO(String name, LocalDate birthday, String cpf, Long mr) {
        super(name, birthday, cpf);
        this.mr = mr;
    }

    public Long getMr() {
        return mr;
    }

    public void setMr(Long mr) {
        this.mr = mr;
    }
}
