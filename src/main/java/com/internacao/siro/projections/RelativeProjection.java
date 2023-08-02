package com.internacao.siro.projections;

import java.time.LocalDate;

public interface RelativeProjection {
    Long getId();
    String getName();
    LocalDate getBirthday();
    String getRelationship();
}
