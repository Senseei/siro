package com.internacao.siro.entities;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Embeddable
public class DoctorId {
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", unique = true)
    private Person person;
    private Long crm;

    public DoctorId() {}

    public DoctorId(Person person, Long crm) {
        setPerson(person);
        setCrm(crm);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getCrm() {
        return crm;
    }

    public void setCrm(Long crm) {
        this.crm = crm;
    }

    @Override
	public int hashCode() {
		return Objects.hash(person);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoctorId other = (DoctorId) obj;
		return Objects.equals(person, other.person);
	}
}