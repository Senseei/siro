package com.internacao.siro.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_employees")
public class Employee {
    
    @Id
    private Long re;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id")
    private Person person;

    public Employee() {}

    public Employee(Person person, Long re) {
        setPerson(person);
        setRe(re);
    }

    public Employee(String name, LocalDate birthday, Long re) {
        Person person = new Person(name, birthday);
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
		return Objects.hash(re);
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
		return Objects.equals(re, other.re);
	}
}