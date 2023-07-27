package com.internacao.siro.entities;

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

    public Relative(Person person, Patient patient, String relationship) {
        setPerson(person);
        setPatient(patient);
        setRelationship(relationship);
    }

    public Person getPerson() {
        return id.getPerson();
    }

    public void setPerson(Person person) {
        id.setPerson(person);
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
}