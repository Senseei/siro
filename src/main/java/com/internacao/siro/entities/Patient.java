package com.internacao.siro.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.internacao.siro.dto.patient.NewPatientDTO;
import com.internacao.siro.dto.patient.UpdatePatientDTO;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends Person {

    private Long mr;

    public Patient() {}

	public Patient(String name, Long mr) {
        super(name);
        this.mr = mr;
    }

    public Patient(String name, LocalDate birthday, Long mr) {
        super(name, birthday);
        this.mr = mr;
    }

	public Patient(String name, LocalDate birthday, String cpf, Long mr) {
        super(name, birthday, cpf);
        this.mr = mr;
    }

    public Patient(NewPatientDTO body) {
        super(body);
        mr = body.getMr();
    }

    public Long getMr() {
        return mr;
    }

    public void setMr(Long mr) {
        this.mr = mr;
    }

    public void updatePatient(UpdatePatientDTO body) {
        updatePerson(body);
        if (body.getMr() != null)
            mr = body.getMr();
    }

    public void reverseDelete(NewPatientDTO body) {
        super.reverseDelete(body);
        mr = body.getMr();
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