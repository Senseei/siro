package com.internacao.siro.dto.documentation;

public class CancelDocumentationDTO {
    
    private Long id;
    private Long employeeId;
    private String cause;

    public CancelDocumentationDTO() {}

    public CancelDocumentationDTO(Long employeeId, String cause) {
        this.employeeId = employeeId;
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
