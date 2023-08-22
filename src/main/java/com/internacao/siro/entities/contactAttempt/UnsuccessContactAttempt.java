package com.internacao.siro.entities.contactAttempt;

import java.time.LocalDateTime;

import com.internacao.siro.entities.Employee;
import com.internacao.siro.entities.Register;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Unsuccess")
public class UnsuccessContactAttempt extends ContactAttempt {
    
    private String reasonForNotCalling;

    public UnsuccessContactAttempt() {}

    public UnsuccessContactAttempt(Register register, Employee employee, String reasonForNotCalling) {
        super(register, employee);
        this.reasonForNotCalling = reasonForNotCalling;
    }

    public UnsuccessContactAttempt(Register register, Employee employee, String phoneNumber, LocalDateTime attemptTime, String reasonForNotCalling) {
        super(register, employee, phoneNumber, attemptTime);
        this.reasonForNotCalling = reasonForNotCalling;
    }

    public String getReasonForNotCalling() {
        return reasonForNotCalling;
    }

    public void setReasonForNotCalling(String reasonForNotCalling) {
        this.reasonForNotCalling = reasonForNotCalling;
    }
}
