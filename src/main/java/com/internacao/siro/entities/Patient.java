package com.internacao.siro.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.internacao.siro.dto.patient.NewPatientDTO;
import com.internacao.siro.dto.patient.UpdatePatientDTO;
import com.internacao.siro.exceptions.NegativeMedicalRecordException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("Patient")
public class Patient extends Person {

    private Long mr;

    @OneToMany(mappedBy = "id.patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Relative> relatives = new ArrayList<>();

    public Patient() {
    }

    public Patient(String name, Long mr) {
        super(name);
        setMr(mr);
    }

    public Patient(String name, LocalDate birthday, Long mr) {
        super(name, birthday);
        setMr(mr);
    }

    public Patient(String name, LocalDate birthday, String cpf, Long mr) {
        super(name, birthday, cpf);
        setMr(mr);
    }

    public Patient(NewPatientDTO body) {
        super(body);
        setMr(body.getMr());
    }

    public Long getMr() {
        return mr;
    }

    public void setMr(Long mr) {
        if (mr < 0)
            throw new NegativeMedicalRecordException("Patient's medical record must be a positive integer number");
        this.mr = mr;
    }

    public void update(UpdatePatientDTO body) {
        super.update(body);
        if (body.getMr() != null)
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