package com.internacao.siro.validators.json;

import org.springframework.stereotype.Component;

import com.internacao.siro.dto.person.NewPersonDTO;
import com.internacao.siro.dto.person.UpdatePersonDTO;

@Component
public class PersonJson extends Json {

    public void validate(NewPersonDTO body) {
        validateField(body.getCpf(), "CPF");
        validateField(body.getName(), "Name");
        validateField(body.getBirthday(), "Birthday");

        fieldIsEmpty(body.getCpf(), "CPF");
        fieldIsEmpty(body.getName(), "Name");
    }

    public void validate(UpdatePersonDTO body) {
        fieldIsEmpty(body.getCpf(), "CPF");
        fieldIsEmpty(body.getName(), "Name");
    }
}
