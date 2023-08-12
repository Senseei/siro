package com.internacao.siro.dto.patient;

import java.time.LocalDate;

import com.internacao.siro.dto.person.UpdatePersonDTO;

public class UpdatePatientDTO extends UpdatePersonDTO {

    private Long mr;

    public UpdatePatientDTO() {}

    public UpdatePatientDTO(String name, LocalDate birthday, String cpf, Long mr) {
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
