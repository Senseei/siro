package com.internacao.siro.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.internacao.siro.dto.contactAttempt.NewSuccessContactAttemptDTO;
import com.internacao.siro.dto.contactAttempt.NewUnsuccessContactAttemptDTO;
import com.internacao.siro.dto.documentation.CancelDocumentationDTO;
import com.internacao.siro.dto.documentation.NewDocumentationDTO;
import com.internacao.siro.dto.occurrence.NewOccurrenceDTO;
import com.internacao.siro.dto.register.NewRegisterDTO;
import com.internacao.siro.dto.register.UpdateRegisterDTO;
import com.internacao.siro.exception.InvalidJsonFormatException;

import jakarta.persistence.EntityNotFoundException;

@Component
public class Json {

    @Autowired
    Repositories repositories;

    public void validate(NewSuccessContactAttemptDTO body) {
        validateEntityField(body.getEmployeeId(), repositories.employeeRepository, "Employee");
        validateField(body.getPhoneNumber(), "Phone number");
        validateField(body.getAttemptTime(), "Attempt time");
        validateField(body.getPersonWhoAnswered(), "Person who answered");
        validateField(body.getRelationship(), "Relationship");
    }

    public void validate(NewUnsuccessContactAttemptDTO body) {
        validateEntityField(body.getEmployeeId(), repositories.employeeRepository, "Employee");
        validateField(body.getReasonForNotCalling(), "Reason for not calling");
        if (body.getPhoneNumber() != null)
            validateField(body.getAttemptTime(), "Attempt time");
    }

    public void validate(CancelDocumentationDTO body) {
        if (body.getCause() == null)
            throw new InvalidJsonFormatException("Cause cannot be null");
        validateEntityField(body.getEmployeeId(), repositories.employeeRepository, "Employee");
    }

    public void validate(NewDocumentationDTO body) {
        if (body.getDoc() == null)
            throw new InvalidJsonFormatException("Doc number cannot be null");
        validateEntityExistence(body.getDoctorId(), repositories.doctorRepository, "Doctor");
    }

    public void validate(NewOccurrenceDTO body) {
        validateEntityField(body.getEmployeeId(), repositories.employeeRepository, "Employee");
        validateField(body.getDescription(), "Description");
    }

    public void validate(UpdateRegisterDTO body) {

        validateEntityExistence(body.getPatientId(), repositories.patientRepository, "patient");
        validateEntityExistence(body.getDoctorId(), repositories.doctorRepository, "doctor");
        validateEntityExistence(body.getClinicId(), repositories.clinicRepository, "clinic");
        validateEntityExistence(body.getEmployeeId(), repositories.employeeRepository, "employee");
        if (body.getRelative() != null)
            validateEntityExistence(body.getRelative().getId(), repositories.personRepository, "relative");
    }

    public void validate(NewRegisterDTO body) {
        validateEntityField(body.getClinicId(), repositories.clinicRepository, "Clinic");
        validateEntityField(body.getDoctorId(), repositories.doctorRepository, "Doctor");
        validateEntityField(body.getPatientId(), repositories.patientRepository, "Patient");
    }
    
    public void validateEntityField(Long entityId, JpaRepository<?, Long> repository, String entityName) {
        if (entityId == null)
            throw new InvalidJsonFormatException(entityName + " Id cannot be null");
        validateEntityExistence(entityId, repository, entityName);
    }

    public void validateEntityExistence(Long entityId, JpaRepository<?, Long> repository, String entityName) {
        if (entityId != null && !repository.existsById(entityId)) {
            throw new EntityNotFoundException("The " + entityName + " with the given Id doesn't exist");
        }
    }

    public void validateField(Object field, String fieldName) {
        if (field == null)
            throw new InvalidJsonFormatException(fieldName + " cannot be null");
    }
}
