package com.internacao.siro.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.internacao.siro.dto.doctor.NewDoctorDTO;
import com.internacao.siro.dto.doctor.UpdateDoctorDTO;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DOCTOR")
public class Doctor extends Person {

    @Column(unique = true)
    private Long crm;

    public Doctor() {}

	public Doctor(String name, Long crm) {
        super(name);
        this.crm = crm;
    }

    public Doctor(String name, LocalDate birthday, Long crm) {
        super(name, birthday);
        this.crm = crm;
    }

	public Doctor(String name, LocalDate birthday, String cpf, Long crm) {
        super(name, birthday, cpf);
        this.crm = crm;
    }

    public Doctor(NewDoctorDTO dto) {
        super(dto.getName(), dto.getBirthday());
        this.crm = dto.getCrm();
    }

    public Long getCrm() {
        return crm;
    }

    public void setCrm(Long crm) {
        this.crm = crm;
    }

    public void updateDoctor(UpdateDoctorDTO dto) {
        if (dto.getName() != null)
            setName(dto.getName());
        if (dto.getBirthday() != null)
            setBirthday(dto.getBirthday());
        if (dto.getCpf() != null)
            setCpf(dto.getCpf());
        if (dto.getCrm() != null)
            crm = dto.getCrm();
    }

    public void reverseDelete(NewDoctorDTO dto) {
        setName(dto.getName());
        setBirthday(dto.getBirthday());
        setCpf(dto.getCpf());
        crm = dto.getCrm();
        setDeletedAt(null);
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