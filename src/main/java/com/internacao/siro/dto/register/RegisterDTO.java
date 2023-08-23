package com.internacao.siro.dto.register;

import java.time.LocalDateTime;
import java.util.List;

import com.internacao.siro.dto.clinic.ClinicDTO;
import com.internacao.siro.dto.contactAttempt.ContactAttemptDTO;
import com.internacao.siro.dto.doctor.DoctorMinDTO;
import com.internacao.siro.dto.documentation.DocumentationDTO;
import com.internacao.siro.dto.employee.EmployeeMinDTO;
import com.internacao.siro.dto.occurrence.OccurrenceDTO;
import com.internacao.siro.dto.patient.PatientDTO;
import com.internacao.siro.dto.relative.RelativeDTO;
import com.internacao.siro.entities.Register;
import com.internacao.siro.entities.Relative;
import com.internacao.siro.entities.contactAttempt.ContactAttempt;

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
        id = register.getId();
        patient = PatientDTO.of(register.getPatient());
        dateOfDeath = register.getDateOfDeath();
        documentation = register.getDocumentation().stream().map(x -> DocumentationDTO.of(x)).toList();
        doctor = DoctorMinDTO.of(register.getDoctor());
        clinic = ClinicDTO.of(register.getClinic());
        contactAttempts = register.getContactAttempts().stream().map(x -> ContactAttempt.toDTO(x)).toList();
        documentationWithdrawal = register.getDocumentationWithdrawal();
        attendant = EmployeeMinDTO.of(register.getAttendant());
        occurrences = register.getOccurrences().stream().map(x -> OccurrenceDTO.of(x)).toList();
    }

    public RegisterDTO(Register register, Relative relative) {
        id = register.getId();
        patient = PatientDTO.of(register.getPatient());
        dateOfDeath = register.getDateOfDeath();
        documentation = register.getDocumentation().stream().map(x -> DocumentationDTO.of(x)).toList();
        doctor = DoctorMinDTO.of(register.getDoctor());
        clinic = ClinicDTO.of(register.getClinic());
        contactAttempts = register.getContactAttempts().stream().map(x -> ContactAttempt.toDTO(x)).toList();
        this.relative = RelativeDTO.of(relative);
        documentationWithdrawal = register.getDocumentationWithdrawal();
        attendant = EmployeeMinDTO.of(register.getAttendant());
        occurrences = register.getOccurrences().stream().map(x -> OccurrenceDTO.of(x)).toList();
    }

    public static RegisterDTO of(Register register) {
        if (register == null)
            return null;
        return new RegisterDTO(register);
    }

    public static RegisterDTO of(Register register, Relative relative) {
        if (register == null)
            return null;
        return new RegisterDTO(register, relative);
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
