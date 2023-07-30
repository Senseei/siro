package com.internacao.siro.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_contact_attempts")
public class ContactAttempt {

    private static final DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "register_id")
    private Register register;

    @OneToOne
    @JoinColumn(name = "employee_re")
    private Employee employee;
    private String phoneNumber;
    private LocalDateTime attemptTime;
    private String reasonForNotCalling;

    public ContactAttempt() {}

    public ContactAttempt(Register register, Employee employee, String reasonForNotCalling) {
        setRegister(register);
        setEmployee(employee);
        setReasonForNotCalling(reasonForNotCalling);
    }

    public ContactAttempt(Register register, Employee employee, String phoneNumber, LocalDateTime attemptTime, String reasonForNotCalling) {
        setAttemptTime(attemptTime);
        setEmployee(employee);
        setPhoneNumber(phoneNumber);
        setReasonForNotCalling(reasonForNotCalling);
        setRegister(register);
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getAttemptTime() {
        return attemptTime;
    }

    public String getFormattedAttemptTime() {
        return attemptTime.format(DATETIMEFORMATTER);
    }

    public void setAttemptTime(LocalDateTime attemptTime) {
        this.attemptTime = attemptTime;
    }

    public String getReasonForNotCalling() {
        return reasonForNotCalling;
    }

    public void setReasonForNotCalling(String reasonForNotCalling) {
        this.reasonForNotCalling = reasonForNotCalling;
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
        ContactAttempt other = (ContactAttempt) obj;
        return Objects.equals(id, other.id);
    }
}
