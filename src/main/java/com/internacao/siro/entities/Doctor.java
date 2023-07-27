package com.internacao.siro.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_doctors")
public class Doctor {

    @EmbeddedId
    private DoctorId id = new DoctorId();

    public Doctor() {}
    
    public Doctor(Person person, Long crm) {
        id.setPerson(person);
        id.setCrm(crm);
    }

    public Doctor(String name, LocalDate birthday, Long crm) {
        Person person = new Person(name, birthday);
        id.setPerson(person);
        id.setCrm(crm);
    }

    public Person getPerson() {
        return id.getPerson();
    }

    public void setPerson(Person person) {
        id.setPerson(person);
    }

    public Long getCrm() {
        return id.getCrm();
    }

    public void setCrm(Long crm) {
        id.setCrm(crm);
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
		Doctor other = (Doctor) obj;
		return Objects.equals(id, other.id);
	}
}