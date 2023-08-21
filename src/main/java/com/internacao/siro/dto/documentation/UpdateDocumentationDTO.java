package com.internacao.siro.dto.documentation;

public class UpdateDocumentationDTO {
    
    private String doc;

    public UpdateDocumentationDTO() {}

    public UpdateDocumentationDTO(String doc) {
        this.doc = doc;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }
}
