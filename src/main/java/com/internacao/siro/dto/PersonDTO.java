package com.internacao.siro.dto;

import java.time.LocalDate;

import com.internacao.siro.entities.Person;

public class PersonDTO {

    private Long id;
    private String name;
    private LocalDate birthday;

    public PersonDTO() {}

    public PersonDTO(Person person) {
        if (person != null) {
            id = person.getId();
            name = person.getName();
            birthday = person.getBirthday();
        }
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
