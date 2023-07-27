package com.internacao.siro.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;

@Entity
@Table(name = "tb_employees")
public class Employee {
    @EmbeddedId
    private EmployeeId id = new EmployeeId();

    public Employee() {}

    public Employee(Person person, Long re) {
        id.setPerson(person);
        id.setRe(re);
    }

    public Person getPerson() {
        return id.getPerson();
    }

    public void setPerson(Person person) {
        id.setPerson(person);
    }

    public Long getRe() {
        return id.getRe();
    }

    public void setRe(Long re) {
        id.setRe(re);
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
		Employee other = (Employee) obj;
		return Objects.equals(id, other.id);
	}
}