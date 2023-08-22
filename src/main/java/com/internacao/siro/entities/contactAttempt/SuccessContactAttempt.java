package com.internacao.siro.entities.contactAttempt;

import java.time.LocalDateTime;

import com.internacao.siro.entities.Employee;
import com.internacao.siro.entities.Register;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Success")
public class SuccessContactAttempt extends ContactAttempt {
    
    private String personWhoAnswered;
    private String relationship;

    public SuccessContactAttempt() {}

    public SuccessContactAttempt(Register register, Employee employee, String phoneNumber, LocalDateTime attemptTime,
        String personWhoAnswered, String relationship) {
        super(register, employee, phoneNumber, attemptTime);
        this.personWhoAnswered = personWhoAnswered;
        this.relationship = relationship;
    }

    public String getPersonWhoAnswered() {
        return personWhoAnswered;
    }

    public void setPersonWhoAnswered(String personWhoAnswered) {
        this.personWhoAnswered = personWhoAnswered;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
