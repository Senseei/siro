package com.internacao.siro.dto.register;

import java.time.LocalDateTime;

public class UpdateRegisterDTO {
    
    private Long patientId;
    private LocalDateTime dateOfDeath;
    private Long doctorId;
    private Long clinicId;
    private Long relativeId;
    private LocalDateTime documentationWithdrawal;
    private Long employeeId;

    public UpdateRegisterDTO() {}

    public UpdateRegisterDTO(Long patiendId, LocalDateTime dateOfDeath, Long doctorId,
        Long clinicId, Long relativeId, LocalDateTime documentationWithdrawal,
        Long employeeId) {
            this.patientId = patiendId;
            this.dateOfDeath = dateOfDeath;
            this.doctorId = doctorId;
            this.clinicId = clinicId;
            this.relativeId = relativeId;
            this.documentationWithdrawal = documentationWithdrawal;
            this.employeeId = employeeId;
        }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public LocalDateTime getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(LocalDateTime dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getDocumentationWithdrawal() {
        return documentationWithdrawal;
    }

    public void setDocumentationWithdrawal(LocalDateTime documentationWithdrawal) {
        this.documentationWithdrawal = documentationWithdrawal;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(Long relativeId) {
        this.relativeId = relativeId;
    }
}
