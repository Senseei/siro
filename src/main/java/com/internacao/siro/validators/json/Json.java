package com.internacao.siro.validators.json;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.internacao.siro.exceptions.InvalidJsonFormatException;

import jakarta.persistence.EntityNotFoundException;

@Component
public class Json {
    
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
