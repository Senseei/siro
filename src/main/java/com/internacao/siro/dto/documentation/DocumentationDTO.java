package com.internacao.siro.dto.documentation;

import com.internacao.siro.entities.Documentation;

public class DocumentationDTO {
    
    private Long id;
    private String doc;

    public DocumentationDTO() {}

    public DocumentationDTO(Documentation documentation) {
        id = documentation.getId();
        doc = documentation.getDoc();
    }

    public static DocumentationDTO of(Documentation documentation) {
        if (documentation == null)
            return null;
        return new DocumentationDTO(documentation);
    }

    public String getDoc() {
        return doc;
    }

    public Long getId() {
        return id;
    }
}
