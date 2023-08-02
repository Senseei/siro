package com.internacao.siro.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.internacao.siro.entities.Register;

public class RegisterDTO {
    
    private Long id;
    private PatientDTO patient;
    private LocalDateTime dateOfDeath;
    private List<DocumentationDTO> documentation;
    private DoctorDTO doctor;
    private ClinicDTO clinic;
    private List<ContactAttemptDTO> contactAttempts;
    private LocalDateTime documentationWithdrawal;
    private EmployeeMinDTO attendant;
    private List<OccurrenceDTO> occurrences;

    public RegisterDTO() {}

    public RegisterDTO(Register register) {
        if (register != null) {
            id = register.getId();
            patient = new PatientDTO(register.getPatient());
            dateOfDeath = register.getDateOfDeath();
            documentation = register.getDocumentation().stream().map(x -> new DocumentationDTO(x)).toList();
            doctor = new DoctorDTO(register.getDoctor());
            clinic = new ClinicDTO(register.getClinic());
            contactAttempts = register.getContactAttempts().stream().map(x -> new ContactAttemptDTO(x)).toList();
            documentationWithdrawal = register.getDocumentationWithdrawal();
            attendant = new EmployeeMinDTO(register.getAttendant());
            occurrences = register.getOccurrences().stream().map(x -> new OccurrenceDTO(x)).toList();
        }
    }

    public EmployeeMinDTO getAttendant() {
        return attendant;
    }

    public LocalDateTime getDocumentationWithdrawal() {
        return documentationWithdrawal;
    }

    public ClinicDTO getClinic() {
        return clinic;
    }

    public List<ContactAttemptDTO> getContactAttempts() {
        return contactAttempts;
    }

    public LocalDateTime getDateOfDeath() {
        return dateOfDeath;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public List<DocumentationDTO> getDocumentation() {
        return documentation;
    }

    public Long getId() {
        return id;
    }

    public List<OccurrenceDTO> getOccurrences() {
        return occurrences;
    }

    public PatientDTO getPatient() {
        return patient;
    }
}
