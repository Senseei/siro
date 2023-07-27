package com.internacao.siro.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PatientId {
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    private Long mr;

    public PatientId() {}

    public PatientId(Person person, Long mr) {
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
		return Objects.hash(person, mr);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientId other = (PatientId) obj;
		return Objects.equals(person, other.person) && Objects.equals(mr, other.mr);
	}
}