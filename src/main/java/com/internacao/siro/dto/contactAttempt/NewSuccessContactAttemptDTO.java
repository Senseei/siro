package com.internacao.siro.dto.contactAttempt;

import java.time.LocalDateTime;

public class NewSuccessContactAttemptDTO {
    
    private Long employeeId;
    private String phoneNumber;
    private LocalDateTime attemptTime;
    private String personWhoAnswered;
    private String relationship;

    public NewSuccessContactAttemptDTO() {}

    public NewSuccessContactAttemptDTO(Long employeeId, String phoneNumber, LocalDateTime attemptTime,
        String personWhoAnswered, String relationship) {
        this.employeeId = employeeId;
        this.phoneNumber = phoneNumber;
        this.attemptTime = attemptTime;
        this.personWhoAnswered = personWhoAnswered;
        this.relationship = relationship;
    }

    public LocalDateTime getAttemptTime() {
        return attemptTime;
    }

    public void setAttemptTime(LocalDateTime attemptTime) {
        this.attemptTime = attemptTime;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPersonWhoAnswered() {
        return personWhoAnswered;
    }

    public void setPersonWhoAnswered(String personWhoAnswered) {
        this.personWhoAnswered = personWhoAnswered;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
