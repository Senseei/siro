package com.internacao.siro.dto.documentation;

import com.internacao.siro.dto.doctor.DoctorMinDTO;
import com.internacao.siro.entities.Documentation;

public class DocumentationDTO {
    
    private Long id;
    private String doc;
    private DoctorMinDTO doctor;
    private boolean canceled;

    public DocumentationDTO() {}

    public DocumentationDTO(Documentation documentation) {
        id = documentation.getId();
        doc = documentation.getDoc();
        doctor = DoctorMinDTO.of(documentation.getDoctor());
        canceled = documentation.isCanceled();
    }

    public static DocumentationDTO of(Documentation documentation) {
        if (documentation == null)
            return null;
        return new DocumentationDTO(documentation);
    }

    public boolean isCanceled() {
        return canceled;
    }

    public String getDoc() {
        return doc;
    }

    public DoctorMinDTO getDoctor() {
        return doctor;
    }

    public Long getId() {
        return id;
    }
}
