package com.internacao.siro.projections;

import java.time.LocalDate;

public interface EmployeeProjection {
    Long getEmployeeId();
    Long getPersonId();
    String getName();
    LocalDate getBirthday();
    Long getRe();
}
