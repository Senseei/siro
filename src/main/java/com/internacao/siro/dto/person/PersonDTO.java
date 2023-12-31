package com.internacao.siro.dto.person;

import java.time.LocalDate;

import com.internacao.siro.entities.Person;

public class PersonDTO {

    private Long id;
    private String name;
    private LocalDate birthday;
    private String cpf;

    public PersonDTO() {}

    public PersonDTO(Person person) {
        id = person.getId();
        name = person.getName();
        birthday = person.getBirthday();
        cpf = person.getCpf();
    }

    public static PersonDTO of(Person person) {
        if (person == null)
            return null;
        return new PersonDTO(person);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getCpf() {
        return cpf;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
