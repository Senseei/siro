package com.internacao.siro.dto;

import java.time.LocalDate;

import com.internacao.siro.projections.RelativeProjection;

public class RelativeDTO {
    
    private Long id;
    private String name;
    private LocalDate birthday;
    private String relationship;

    public RelativeDTO() {}

    public RelativeDTO(RelativeProjection projection) {
        if (projection != null) {
            id = projection.getId();
            name = projection.getName();
            birthday = projection.getBirthday();
            relationship = projection.getRelationship();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getRelationship() {
        return relationship;
    }
}