package com.internacao.siro.dto.documentation;

public class NewDocumentationDTO {

    private String doc;

    public NewDocumentationDTO() {}

    public NewDocumentationDTO(String type, String doc) {
        this.doc = doc;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }
}
