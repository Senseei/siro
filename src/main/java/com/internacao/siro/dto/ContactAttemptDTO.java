package com.internacao.siro.dto;

import java.time.LocalDateTime;

import com.internacao.siro.entities.ContactAttempt;

public class ContactAttemptDTO {
    
    private Long id;
    private EmployeeMinDTO employee;
    private String phoneNumber;
    private LocalDateTime attemptTime;
    private String reasonForNotCalling;

    public ContactAttemptDTO() {}

    public ContactAttemptDTO(ContactAttempt contactAttempt) {
        id = contactAttempt.getId();
        employee = new EmployeeMinDTO(contactAttempt.getEmployee());
        phoneNumber = contactAttempt.getPhoneNumber();
        attemptTime = contactAttempt.getAttemptTime();
        reasonForNotCalling = contactAttempt.getReasonForNotCalling();
    }

    public LocalDateTime getAttemptTime() {
        return attemptTime;
    }

    public EmployeeMinDTO getEmployee() {
        return employee;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getReasonForNotCalling() {
        return reasonForNotCalling;
    }
}
