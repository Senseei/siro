package com.internacao.siro.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_patients")
public class Patient {

    @Id
    private Long mr;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", unique = true)
    private Person person;

    @OneToMany(mappedBy = "id.patient")
    private List<Relative> relatives;

    public Patient() {}

    public Patient(Person person, Long mr) {
        setPerson(person);
        setMr(mr);
    }

    public Patient(String name, LocalDate birthday, Long mr) {
        Person person = new Person(name, birthday);
        setPerson(person);
        setMr(mr);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getMr() {
        return mr;
    }

    public void setMr(Long mr) {
        this.mr = mr;
    }

    @Override
	public int hashCode() {
		return Objects.hash(mr);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(mr, other.mr);
	}
}