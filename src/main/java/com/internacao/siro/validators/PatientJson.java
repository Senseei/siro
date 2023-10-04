package com.internacao.siro.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.internacao.siro.dto.patient.NewPatientDTO;
import com.internacao.siro.dto.patient.UpdatePatientDTO;
import com.internacao.siro.exceptions.InvalidJsonFormatException;
import com.internacao.siro.exceptions.NegativeMedicalRecordException;
import com.internacao.siro.repositories.PatientRepository;
import com.internacao.siro.validators.json.PersonJson;

@Component
public class PatientJson extends PersonJson {

    @Autowired
    PatientRepository patientRepository;

    public void validate(NewPatientDTO body) {
        super.validate(body);
        validateMr(body.getMr(), false);
    }

    public void validate(UpdatePatientDTO body) {
        super.validate(body);
        validateMr(body.getMr(), true);
    }

    public void fieldIsEmpty(String field, String fieldName) {
        if (field != null && field.trim().equals(""))
            throw new InvalidJsonFormatException(fieldName + " cannot be empty");
    }

    void validateMr(Long mr, boolean nullable) {
        if (!nullable)
            validateField(mr, "Medical Record (MR)");
        if (mr < 0)
            throw new NegativeMedicalRecordException();
    }
}
