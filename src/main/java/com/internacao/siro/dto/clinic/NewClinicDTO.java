package com.internacao.siro.dto.clinic;

public class NewClinicDTO {
    
    private String name;

    public NewClinicDTO() {}

    public NewClinicDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
