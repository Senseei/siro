package com.internacao.siro.dto.clinic;

public class UpdateClinicDTO {
    
    private String name;

    public UpdateClinicDTO() {}

    public UpdateClinicDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
