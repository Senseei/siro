package com.internacao.siro.dto.relative;

public class UpdateRelativeDTO {
    
    private Long id;
    private String relationship;

    public UpdateRelativeDTO() {}

    public UpdateRelativeDTO(Long id, String relationship) {
        this.id = id;
        this.relationship = relationship;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
