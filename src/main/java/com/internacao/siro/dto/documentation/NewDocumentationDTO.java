package com.internacao.siro.dto.documentation;

public class NewDocumentationDTO {

    private String doc;
    private Long doctorId;

    public NewDocumentationDTO() {}

    public NewDocumentationDTO(String doc, Long doctorId) {
        this.doc = doc;
        this.doctorId = doctorId;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
