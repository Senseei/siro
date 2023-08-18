package com.internacao.siro.dto.person;

import java.time.LocalDate;

import com.internacao.siro.entities.Person;

public class PersonMinDTO {

    private Long id;
    private String name;
    private LocalDate birthday;

    public PersonMinDTO() {}

    public PersonMinDTO(Person person) {
        id = person.getId();
        name = person.getName();
        birthday = person.getBirthday();
    }

    public static PersonMinDTO of(Person person) {
        if (person == null)
            return null;
        return new PersonMinDTO(person);
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
