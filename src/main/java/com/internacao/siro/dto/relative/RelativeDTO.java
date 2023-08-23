package com.internacao.siro.dto.relative;

import java.time.LocalDate;

import com.internacao.siro.entities.Relative;

public class RelativeDTO {
    
    private Long id;
    private String name;
    private LocalDate birthday;
    private String relationship;

    public RelativeDTO() {}

    public RelativeDTO(Relative relative) {
        id = relative.getRelative().getId();
        name = relative.getRelative().getName();
        birthday = relative.getRelative().getBirthday();
        relationship = relative.getRelationship();
    }

    public static RelativeDTO of(Relative relative) {
        if (relative == null)
            return null;
        return new RelativeDTO(relative);
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