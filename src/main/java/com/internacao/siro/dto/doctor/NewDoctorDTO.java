package com.internacao.siro.dto.doctor;

import java.time.LocalDate;

public class NewDoctorDTO {
    
    private String name;
    private LocalDate birthday;
    private String cpf;
    private Long crm;

    public NewDoctorDTO() {}

    public NewDoctorDTO(String name, LocalDate birthday, String cpf, Long crm) {
        this.name = name;
        this.birthday = birthday;
        this.cpf = cpf;
        this.crm = crm;
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

    public Long getCrm() {
        return crm;
    }

    public void setCrm(Long crm) {
        this.crm = crm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
