package com.internacao.siro.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;

@Entity
@Table(name = "tb_patients")
public class Patient {

    @EmbeddedId
    private PatientId id = new PatientId();

    public Patient() {}

    public Patient(Person person, Long mr) {
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