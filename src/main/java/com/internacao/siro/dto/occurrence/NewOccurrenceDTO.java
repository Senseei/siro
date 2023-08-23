package com.internacao.siro.dto.occurrence;

public class NewOccurrenceDTO {
    
    private Long employeeId;
    private String description;

    public NewOccurrenceDTO() {}

    public NewOccurrenceDTO(Long employeeId, String description) {
        this.employeeId = employeeId;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
