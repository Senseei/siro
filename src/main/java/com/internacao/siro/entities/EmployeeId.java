package com.internacao.siro.entities;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Embeddable
public class EmployeeId {

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", unique = true)
    private Person person;
    private Long re;

    public EmployeeId() {}

    public EmployeeId(Person person, Long re) {
        setPerson(person);
        setRe(re);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getRe() {
        return re;
    }

    public void setRe(Long re) {
        this.re = re;
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
		EmployeeId other = (EmployeeId) obj;
		return Objects.equals(person, other.person);
	}
}