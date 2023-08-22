package com.internacao.siro.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.internacao.siro.dto.doctor.DoctorDTO;
import com.internacao.siro.dto.employee.EmployeeDTO;
import com.internacao.siro.dto.patient.PatientDTO;
import com.internacao.siro.dto.person.NewPersonDTO;
import com.internacao.siro.dto.person.PersonDTO;
import com.internacao.siro.dto.person.UpdatePersonDTO;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_people")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthday;
    @Column(unique = true)
    private String cpf;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public Person(String name, LocalDate birthday, String cpf) {
        if (cpf != null && cpf.length() != 11)
            throw new IllegalArgumentException("CPF must have exactly 11 characters");
        this.name = name;
        this.birthday = birthday;
        this.cpf = cpf;
    }

    public Person(NewPersonDTO body) {
        name = body.getName();
        birthday = body.getBirthday();
        cpf = body.getCpf();
    }

    public static PersonDTO toDTO (Person person) {
        if (person instanceof Doctor)
            return DoctorDTO.of((Doctor) person);
        if (person instanceof Employee)
            return EmployeeDTO.of((Employee) person);
        if (person instanceof Patient)
            return PatientDTO.of((Patient) person);
        return PersonDTO.of(person);
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

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void update(UpdatePersonDTO body) {
        if (body.getName() != null)
            setName(body.getName());
        if (body.getBirthday() != null)
            setBirthday(body.getBirthday());
        if (body.getCpf() != null)
            setCpf(body.getCpf());
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