package com.internacao.siro.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.internacao.siro.exception.InvalidJsonFormatException;
import com.internacao.siro.repositories.ClinicRepository;
import com.internacao.siro.repositories.DoctorRepository;
import com.internacao.siro.repositories.EmployeeRepository;
import com.internacao.siro.repositories.PatientRepository;
import com.internacao.siro.repositories.PersonRepository;
import com.internacao.siro.repositories.RegisterRepository;
import com.internacao.siro.repositories.RelativeRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class Util {
    
    @Autowired
    protected RegisterRepository registerRepository;
    @Autowired
    protected PersonRepository personRepository;
    @Autowired
    protected EmployeeRepository employeeRepository;
    @Autowired
    protected PatientRepository patientRepository;
    @Autowired
    protected DoctorRepository doctorRepository;
    @Autowired
    protected ClinicRepository clinicRepository;
    @Autowired
    protected RelativeRepository relativeRepository;

    protected void validateJsonEntityField(Long entityId, JpaRepository<?, Long> repository, String entityName) {
        if (entityId == null)
            throw new InvalidJsonFormatException(entityName + " Id cannot be null");
        validateEntityExistence(entityId, repository, entityName);
    }

    protected void validateEntityExistence(Long entityId, JpaRepository<?, Long> repository, String entityName) {
        if (entityId != null && !repository.existsById(entityId)) {
            throw new EntityNotFoundException("The " + entityName + " with the given Id doesn't exist");
        }
    }

    protected void validateJsonField(Object field, String fieldName) {
        if (field == null)
            throw new InvalidJsonFormatException(fieldName + " cannot be null");
    }
}
