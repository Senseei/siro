package com.internacao.siro.dto.contactAttempt;

import com.internacao.siro.entities.contactAttempt.UnsuccessContactAttempt;

public class UnsuccesContactAttemptDTO extends ContactAttemptDTO {
    
    private String reasonForNotCalling;

    public UnsuccesContactAttemptDTO() {}

    public UnsuccesContactAttemptDTO(UnsuccessContactAttempt attempt) {
        super(attempt);
        reasonForNotCalling = attempt.getReasonForNotCalling();
    }

    public static UnsuccesContactAttemptDTO of(UnsuccessContactAttempt attempt) {
        if (attempt == null)
            return null;
        return new UnsuccesContactAttemptDTO(attempt);
    }

    public String getReasonForNotCalling() {
        return reasonForNotCalling;
    }
}
