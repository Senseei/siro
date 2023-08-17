package com.internacao.siro.dto.register;

import java.time.LocalDateTime;

public class NewRegisterDTO {
    
    private Long patientId;
    private LocalDateTime dateOfDeath;
    private Long doctorId;
    private Long clinicId;

    public NewRegisterDTO() {}

    public NewRegisterDTO(Long patientId, LocalDateTime dateOfDeath, Long doctorId, Long clinicId) {
        this.patientId = patientId;
        this.dateOfDeath = dateOfDeath;
        this. doctorId = doctorId;
        this.clinicId = clinicId;
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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
