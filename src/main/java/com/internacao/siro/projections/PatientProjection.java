package com.internacao.siro.projections;

import java.time.LocalDate;

public interface PatientProjection {
    Long getPatientId();
    Long getPersonId();
    Long getMr();
    String getPatientName();
    LocalDate getPatientBirthday();
}
