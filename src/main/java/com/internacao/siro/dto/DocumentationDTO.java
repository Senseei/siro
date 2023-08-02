package com.internacao.siro.dto;

import com.internacao.siro.entities.Documentation;

public class DocumentationDTO {
    
    private Long id;
    private String doc;

    public DocumentationDTO() {}

    public DocumentationDTO(Documentation documentation) {
        id = documentation.getId();
        doc = documentation.getDoc();
    }

    public String getDoc() {
        return doc;
    }

    public Long getId() {
        return id;
    }
}
