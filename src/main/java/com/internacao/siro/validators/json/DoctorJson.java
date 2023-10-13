package com.internacao.siro.validators.json;

import org.springframework.stereotype.Component;

import com.internacao.siro.dto.doctor.NewDoctorDTO;
import com.internacao.siro.dto.doctor.UpdateDoctorDTO;
import com.internacao.siro.exceptions.NegativeCrmNumberException;

@Component
public class DoctorJson extends PersonJson {

    public void validate(NewDoctorDTO body) {
        super.validate(body);
        validateCrm(body.getCrm(), false);
    }

    public void validate(UpdateDoctorDTO body) {
        super.validate(body);
        validateCrm(body.getCrm(), true);
    }

    void validateCrm(Long crm, boolean nullable) {
        if (!nullable)
            validateField(crm, "CRM");
        if (crm != null && crm < 0)
            throw new NegativeCrmNumberException();
    }
}
