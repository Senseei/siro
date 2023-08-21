package com.internacao.siro.entities;

import java.util.Objects;

import com.internacao.siro.dto.clinic.ClinicDTO;
import com.internacao.siro.dto.clinic.NewClinicDTO;
import com.internacao.siro.dto.clinic.UpdateClinicDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_clinics")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Clinic() {}

    public Clinic(String name) {
        setName(name);
    }

    public Clinic(ClinicDTO dto) {
        id = dto.getId();
        name = dto.getName();
    }

    public Clinic(NewClinicDTO body) {
        name = body.getName();
    }

    public void update(UpdateClinicDTO body) {
        if (body.getName() != null)
            name = body.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Clinic other = (Clinic) obj;
        return Objects.equals(id, other.id);
    }
}