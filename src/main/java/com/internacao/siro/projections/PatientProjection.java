package com.internacao.siro.projections;

import java.time.LocalDate;

public interface PatientProjection {
    Long getId();
    Long getMr();
    String getName();
    LocalDate getBirthday();
}
