package com.internacao.siro.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.internacao.siro.dto.doctor.DoctorMinDTO;
import com.internacao.siro.dto.patient.PatientDTO;
import com.internacao.siro.entities.Register;
import com.internacao.siro.projections.RelativeProjection;

public class RegisterDTO {
    
    private Long id;
    private PatientDTO patient;
    private LocalDateTime dateOfDeath;
    private List<DocumentationDTO> documentation;
    private DoctorMinDTO doctor;
    private ClinicDTO clinic;
    private List<ContactAttemptDTO> contactAttempts;
    private RelativeDTO relative;
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
            doctor = new DoctorMinDTO(register.getDoctor());
            clinic = new ClinicDTO(register.getClinic());
            contactAttempts = register.getContactAttempts().stream().map(x -> new ContactAttemptDTO(x)).toList();
            documentationWithdrawal = register.getDocumentationWithdrawal();
            attendant = new EmployeeMinDTO(register.getAttendant());
            occurrences = register.getOccurrences().stream().map(x -> new OccurrenceDTO(x)).toList();
        }
    }

    public RegisterDTO(Register register, RelativeProjection relativeProjection) {
        if (register != null) {
            id = register.getId();
            patient = new PatientDTO(register.getPatient());
            dateOfDeath = register.getDateOfDeath();
            documentation = register.getDocumentation().stream().map(x -> new DocumentationDTO(x)).toList();
            doctor = new DoctorMinDTO(register.getDoctor());
            clinic = new ClinicDTO(register.getClinic());
            contactAttempts = register.getContactAttempts().stream().map(x -> new ContactAttemptDTO(x)).toList();
            relative = new RelativeDTO(relativeProjection);
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

    public DoctorMinDTO getDoctor() {
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

    public RelativeDTO getRelative() {
        return relative;
    }
}
