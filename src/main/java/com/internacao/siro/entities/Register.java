package com.internacao.siro.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private static final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIMEFORMATTER = DateTimeFormatter.ofPattern("HH:mm");

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

    @OneToMany(mappedBy = "register", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ocurrence> ocurrences = new ArrayList<>();
    private LocalDateTime certificateWithdrawal;

    @ManyToOne
    @JoinColumn(name = "employee_re")
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

    public List<ContactAttempt> getContactAttempts() {
        return contactAttempts;
    }

    public List<Documentation> getDocumentation() {
        return documentation;
    }

    public List<Ocurrence> getOcurrences() {
        return ocurrences;
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