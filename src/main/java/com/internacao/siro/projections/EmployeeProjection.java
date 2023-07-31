package com.internacao.siro.projections;

import java.time.LocalDate;

public interface EmployeeProjection {
    Long getId();
    String getName();
    LocalDate getBirthday();
    Long getRe();
}
