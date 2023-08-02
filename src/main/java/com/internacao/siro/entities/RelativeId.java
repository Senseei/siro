package com.internacao.siro.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class RelativeId {
    
    @ManyToOne
    @JoinColumn(name = "relative_id")
    private Person relative;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public RelativeId() {}

    public RelativeId(Person relative, Patient patient) {
        setRelative(relative);
        setPatient(patient);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Person getRelative() {
        return relative;
    }

    public void setRelative(Person relative) {
        this.relative = relative;
    }

    @Override
    public int hashCode() {
        return Objects.hash(relative, patient);
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
        return Objects.equals(relative, other.relative) && Objects.equals(patient, other.patient);
    }
}
