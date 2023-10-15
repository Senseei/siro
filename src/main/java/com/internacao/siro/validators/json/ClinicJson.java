package com.internacao.siro.validators.json;

import org.springframework.stereotype.Component;

import com.internacao.siro.dto.clinic.NewClinicDTO;
import com.internacao.siro.dto.clinic.UpdateClinicDTO;

@Component
public class ClinicJson extends Json {

    public void validate(NewClinicDTO body) {
        validateField(body.getName(), "Name");
        fieldIsEmpty(body.getName(), "Name");
    }

    public void validate(UpdateClinicDTO body) {
        fieldIsEmpty(body.getName(), "Name");
    }
}
