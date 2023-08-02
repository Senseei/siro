package com.internacao.siro.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.internacao.siro.util.GlobalVariables;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_registers")
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "patient_id", unique = true)
    private Patient patient;
    private LocalDateTime dateOfDeath;

    @OneToMany(mappedBy = "register", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documentation> documentation = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @OneToMany(mappedBy = "register", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactAttempt> contactAttempts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "relative_id")
    private Person relative;

    private LocalDateTime documentationWithdrawal;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee attendant;

    @OneToMany(mappedBy = "register", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Occurrence> occurrences = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getDateOfDeath() {
        return dateOfDeath;
    }

    public String getFormattedDateOfDeath() {
        return dateOfDeath.format(GlobalVariables.DATEFORMATTER);
    }

    public String getFormattedTimeOfDeath() {
        return dateOfDeath.format(GlobalVariables.TIMEFORMATTER);
    }

    public void setDateOfDeath(LocalDateTime dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Person getRelative() {
        return relative;
    }

    public void setRelative(Person relative) {
        this.relative = relative;
    }

    public LocalDateTime getDocumentationWithdrawal() {
        return documentationWithdrawal;
    }

    public void setDocumentationWithdrawal(LocalDateTime documentationWithdrawal) {
        this.documentationWithdrawal = documentationWithdrawal;
    }

    public Employee getAttendant() {
        return attendant;
    }

    public void setAttendant(Employee attendant) {
        this.attendant = attendant;
    }

    public List<ContactAttempt> getContactAttempts() {
        return contactAttempts;
    }

    public void setContactAttempts(List<ContactAttempt> contactAttempts) {
        this.contactAttempts = contactAttempts;
    }

    public List<Documentation> getDocumentation() {
        return documentation;
    }

    public void setDocumentation(List<Documentation> documentation) {
        this.documentation = documentation;
    }

    public List<Occurrence> getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(List<Occurrence> occurrences) {
        this.occurrences = occurrences;
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
        Register other = (Register) obj;
        return Objects.equals(id, other.id);
    }
}