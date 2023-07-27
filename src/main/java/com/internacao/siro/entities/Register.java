package com.internacao.siro.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_registers")
public class Register {

    private static final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIMEFORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "patient_id", unique = true)
    private Patient patient;
    private LocalDateTime dateOfDeath;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "relative_id")
    private Relative relative;
    private LocalDateTime certificateWithdrawal;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee attendant;

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
        return dateOfDeath.format(DATEFORMATTER);
    }

    public String getFormattedTimeOfDeath() {
        return dateOfDeath.format(TIMEFORMATTER);
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

    public Relative getRelative() {
        return relative;
    }

    public void setRelative(Relative relative) {
        this.relative = relative;
    }

    public LocalDateTime getCertificateWithdrawal() {
        return certificateWithdrawal;
    }

    public void setCertificateWithdrawal(LocalDateTime certificateWithdrawal) {
        this.certificateWithdrawal = certificateWithdrawal;
    }

    public Employee getAttendant() {
        return attendant;
    }

    public void setAttendant(Employee attendant) {
        this.attendant = attendant;
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