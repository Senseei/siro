package com.internacao.siro.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_patients")
public class Patient {

    @EmbeddedId
    private PatientId id = new PatientId();

    @OneToMany(mappedBy = "id.patient")
    private List<Relative> relatives;

    public Patient() {}

    public Patient(Person person, Long mr) {
        id.setPerson(person);
        id.setMr(mr);
    }

    public Patient(String name, LocalDate birthday, Long mr) {
        Person person = new Person(name, birthday);
        id.setPerson(person);
        id.setMr(mr);
    }

    public Person getPerson() {
        return id.getPerson();
    }

    public void setPerson(Person person) {
        id.setPerson(person);
    }

    public Long getMr() {
        return id.getMr();
    }

    public void setMr(Long mr) {
        id.setMr(mr);
    }

    public List<Relative> getRelatives() {
        return relatives;
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
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}
}