package com.internacao.siro.dto.contactAttempt;

import java.time.LocalDateTime;

public class NewUnsuccessContactAttemptDTO {
    
    private Long employeeId;
    private String phoneNumber;
    private LocalDateTime attemptTime;
    private String reasonForNotCalling;

    public NewUnsuccessContactAttemptDTO() {}

    public NewUnsuccessContactAttemptDTO(Long employeeId, String phoneNumber, LocalDateTime attemptTime,
        String reasonForNotCalling) {
        this.employeeId = employeeId;
        this.phoneNumber = phoneNumber;
        this.attemptTime = attemptTime;
        this.reasonForNotCalling = reasonForNotCalling;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReasonForNotCalling() {
        return reasonForNotCalling;
    }

    public void setReasonForNotCalling(String reasonForNotCalling) {
        this.reasonForNotCalling = reasonForNotCalling;
    }
}
