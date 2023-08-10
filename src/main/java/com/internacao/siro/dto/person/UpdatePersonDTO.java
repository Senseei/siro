package com.internacao.siro.dto.person;

import java.time.LocalDate;

public class UpdatePersonDTO {
    
    private String name;
    private LocalDate birthday;
    private String cpf;

    public UpdatePersonDTO() {}

    public UpdatePersonDTO(String name, LocalDate birthday, String cpf) {
        this.name = name;
        this.birthday = birthday;
        this.cpf = cpf;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
