package com.internacao.siro.entities;

import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_relatives")
public class Relative {
    
    @EmbeddedId
    private RelativeId id = new RelativeId();
    private String relationship;

    public Relative() {}

    public Relative(Person relative, Patient patient, String relationship) {
        id.setRelative(relative);
        id.setPatient(patient);
        this.relationship = relationship;
    }

    public Person getRelative() {
        return id.getRelative();
    }

    public void setRelative(Person relative) {
        id.setRelative(relative);
    }

    public Patient getPatient() {
        return id.getPatient();
    }

    public void setPatient(Patient patient) {
        id.setPatient(patient);
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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
        Relative other = (Relative) obj;
        return Objects.equals(id, other.id) && Objects.equals(relationship, other.relationship);
    }
}
