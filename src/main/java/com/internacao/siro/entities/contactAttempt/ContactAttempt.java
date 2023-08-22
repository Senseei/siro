package com.internacao.siro.entities.contactAttempt;

import java.time.LocalDateTime;
import java.util.Objects;

import com.internacao.siro.dto.contactAttempt.ContactAttemptDTO;
import com.internacao.siro.dto.contactAttempt.SuccessContactAttemptDTO;
import com.internacao.siro.dto.contactAttempt.UnsuccesContactAttemptDTO;
import com.internacao.siro.entities.Employee;
import com.internacao.siro.entities.Register;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_contact_attempts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class ContactAttempt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "register_id")
    private Register register;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private String phoneNumber;
    private LocalDateTime attemptTime;

    public ContactAttempt() {}

    public ContactAttempt(Register register, Employee employee) {
        this.register = register;
        this.employee = employee;
    }

    public ContactAttempt(Register register, Employee employee, String phoneNumber, LocalDateTime attemptTime) {
        this.register = register;
        this.employee = employee;
        this.phoneNumber = phoneNumber;
        this.attemptTime = attemptTime;
    }

    public static ContactAttemptDTO toDTO(ContactAttempt attempt) {
        if (attempt instanceof UnsuccessContactAttempt)
            return UnsuccesContactAttemptDTO.of((UnsuccessContactAttempt) attempt);
        if (attempt instanceof SuccessContactAttempt)
            return SuccessContactAttemptDTO.of((SuccessContactAttempt) attempt);
        return ContactAttemptDTO.of(attempt);
    }

    public LocalDateTime getAttemptTime() {
        return attemptTime;
    }

    public void setAttemptTime(LocalDateTime attemptTime) {
        this.attemptTime = attemptTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
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
