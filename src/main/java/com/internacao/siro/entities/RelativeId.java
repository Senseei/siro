package com.internacao.siro.entities;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Embeddable
public class RelativeId {

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id")
    private Person person;
    
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public RelativeId() {}

    public RelativeId(Person person, Patient patient) {
        setPerson(person);
        setPatient(patient);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public int hashCode() {
        return Objects.hash(person);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RelativeId other = (RelativeId) obj;
        return Objects.equals(person, other.person);
    }
}