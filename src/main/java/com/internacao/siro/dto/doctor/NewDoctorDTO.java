package com.internacao.siro.dto.doctor;

import java.time.LocalDate;

public class NewDoctorDTO {
    
    private String name;
    private LocalDate birthday;
    private Long crm;

    public NewDoctorDTO() {}

    public NewDoctorDTO(String name, LocalDate birthday, Long crm) {
        this.name = name;
        this.birthday = birthday;
        this.crm = crm;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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
