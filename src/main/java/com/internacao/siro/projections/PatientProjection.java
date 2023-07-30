package com.internacao.siro.projections;

import java.time.LocalDate;

public interface PatientProjection {
    Long getPersonId();
    Long getMr();
    String getName();
    LocalDate getBirthday();
}
