package com.internacao.siro.validators.json;

import org.springframework.stereotype.Component;

import com.internacao.siro.dto.patient.NewPatientDTO;
import com.internacao.siro.dto.patient.UpdatePatientDTO;
import com.internacao.siro.exceptions.NegativeMedicalRecordException;

@Component
public class PatientJson extends PersonJson {

    public void validate(NewPatientDTO body) {
        super.validate(body);
        validateMr(body.getMr(), false);
    }

    public void validate(UpdatePatientDTO body) {
        super.validate(body);
        validateMr(body.getMr(), true);
    }

    void validateMr(Long mr, boolean nullable) {
        if (!nullable)
            validateField(mr, "Medical Record (MR)");
        if (mr != null && mr < 0)
            throw new NegativeMedicalRecordException();
    }
}
