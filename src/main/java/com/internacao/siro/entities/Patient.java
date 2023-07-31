package com.internacao.siro.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.internacao.siro.base.Person;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_patients")
@DiscriminatorValue("PATIENT")
public class Patient extends Person {

    private Long mr;

    public Patient() {}

    public Patient(String name, LocalDate birthday, Long mr) {
        super(name, birthday);
        setMr(mr);
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