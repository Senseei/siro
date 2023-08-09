package com.internacao.siro.dto.employee;

import java.time.LocalDate;

public class UpdateEmployeeDTO {
    
    private String name;
    private LocalDate birthday;
    private String cpf;
    private Long re;

    public UpdateEmployeeDTO() {}

    public UpdateEmployeeDTO(String name, LocalDate birthday, String cpf, Long re) {
        this.name = name;
        this.birthday = birthday;
        this.cpf = cpf;
        this.re = re;
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

    public Long getRe() {
        return re;
    }

    public void setRe(Long re) {
        this.re = re;
    }
}
