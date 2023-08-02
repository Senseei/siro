package com.internacao.siro.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DOCTOR")
public class Doctor extends Person {

    private Long crm;

    public Doctor() {}

	public Doctor(String name, Long crm) {
        super(name);
        setCrm(crm);
    }

    public Doctor(String name, LocalDate birthday, Long crm) {
        super(name, birthday);
        setCrm(crm);
    }

	public Doctor(String name, LocalDate birthday, String cpf, Long crm) {
        super(name, birthday, cpf);
        setCrm(crm);
    }

    public Long getCrm() {
        return crm;
    }

    public void setCrm(Long crm) {
        this.crm = crm;
    }

    @Override
	public int hashCode() {
		return Objects.hash(crm);
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
		return Objects.equals(crm, other.crm);
	}
}