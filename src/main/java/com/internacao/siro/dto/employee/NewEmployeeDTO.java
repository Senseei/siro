package com.internacao.siro.dto.employee;

import java.time.LocalDate;

import com.internacao.siro.dto.person.NewPersonDTO;

public class NewEmployeeDTO extends NewPersonDTO {
    
    private Long re;

    public NewEmployeeDTO() {}

    public NewEmployeeDTO(String name, LocalDate birthday, String cpf, Long re) {
        super(name, birthday, cpf);
        this.re = re;
    }

    public Long getRe() {
        return re;
    }

    public void setRe(Long re) {
        this.re = re;
    }
}
