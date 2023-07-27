package com.internacao.siro.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_people")
public class Person {

    private static final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthday;

    public Person() {}

    public Person(String name, LocalDate birthday) {
        setName(name);
        setBirthday(birthday);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getFormattedBirthday() {
        return birthday.format(DATEFORMATTER);
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(id, other.id);
	}
}