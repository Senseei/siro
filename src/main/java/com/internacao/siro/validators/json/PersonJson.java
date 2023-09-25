package com.internacao.siro.validators.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.internacao.siro.dto.person.NewPersonDTO;
import com.internacao.siro.dto.person.UpdatePersonDTO;
import com.internacao.siro.exception.InvalidJsonFormatException;
import com.internacao.siro.repositories.PersonRepository;

@Component
public class PersonJson extends Json {
    
    @Autowired
    PersonRepository personRepository;

    public void validate(NewPersonDTO body) {
        validateField(body.getCpf(), "CPF");
        validateField(body.getName(), "Name");
        validateField(body.getBirthday(), "Birthday");
    }

    public void validate(UpdatePersonDTO body) {
        fieldIsEmpty(body.getCpf(), "CPF");
        fieldIsEmpty(body.getName(), "Name");
    }

    public void fieldIsEmpty(String field, String fieldName) {
        if (field != null && field == "")
            throw new InvalidJsonFormatException(fieldName + " cannot be empty");
    }
}
