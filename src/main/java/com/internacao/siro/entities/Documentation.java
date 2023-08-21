package com.internacao.siro.entities;

import java.util.Objects;

import com.internacao.siro.dto.documentation.DocumentationDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_documentation")
public class Documentation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "register_id")
    private Register register;
    private String doc;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    private boolean canceled;

    public Documentation() {}

    public Documentation(DocumentationDTO dto) {
        id = dto.getId();
        doc = dto.getDoc();
        canceled = dto.isCanceled();
    }

    public Documentation(String doc, Doctor doctor) {
        this.doc = doc;
        this.doctor = doctor;
    }

    public void update(String doc, Doctor doctor) {
        if (doc != null)
            this.doc = doc;
        if (doctor != null)
            this.doctor = doctor;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void cancel() {
        canceled = true;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Long getId() {
        return id;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
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
        Documentation other = (Documentation) obj;
        return Objects.equals(id, other.id);
    }

}