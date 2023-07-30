package com.internacao.siro.projections;

import java.time.LocalDate;

public interface DoctorProjection {
    Long getPersonId();
    String getName();
    LocalDate getBirthday();
    Long getCrm();
}
