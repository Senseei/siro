package com.internacao.siro.dto.contactAttempt;

import com.internacao.siro.entities.contactAttempt.SuccessContactAttempt;

public class SuccessContactAttemptDTO extends ContactAttemptDTO {
    
    private String personWhoAnswered;
    private String relationship;

    public SuccessContactAttemptDTO() {}

    public SuccessContactAttemptDTO(SuccessContactAttempt attempt) {
        super(attempt);
        personWhoAnswered = attempt.getPersonWhoAnswered();
        relationship = attempt.getRelationship();
    }

    public static SuccessContactAttemptDTO of(SuccessContactAttempt attempt) {
        if (attempt == null)
            return null;
        return new SuccessContactAttemptDTO(attempt);
    }

    public String getPersonWhoAnswered() {
        return personWhoAnswered;
    }

    public String getRelationship() {
        return relationship;
    }
}
