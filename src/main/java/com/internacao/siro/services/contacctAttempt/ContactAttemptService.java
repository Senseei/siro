package com.internacao.siro.services.contacctAttempt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.contactAttempt.ContactAttemptDTO;
import com.internacao.siro.dto.contactAttempt.NewSuccessContactAttemptDTO;
import com.internacao.siro.dto.contactAttempt.NewUnsuccessContactAttemptDTO;
import com.internacao.siro.entities.Employee;
import com.internacao.siro.entities.Register;
import com.internacao.siro.entities.contactAttempt.ContactAttempt;
import com.internacao.siro.entities.contactAttempt.SuccessContactAttempt;
import com.internacao.siro.entities.contactAttempt.UnsuccessContactAttempt;
import com.internacao.siro.repositories.ContactAttemptRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ContactAttemptService {
    
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ContactAttemptUtil contactAttemptUtil;
    @Autowired
    ContactAttemptRepository contactAttemptRepository;
    
    @Transactional(readOnly = true)
    public List<ContactAttemptDTO> findAll() {
        List<ContactAttempt> result = contactAttemptRepository.findAll();
        return result.stream().map(x -> ContactAttempt.toDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ContactAttemptDTO> findById(Long id) {
        ContactAttempt attempt = contactAttemptRepository.findById(id).orElse(null);
        if (attempt == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ContactAttempt.toDTO(attempt));
    }

    @Transactional(readOnly = true)
    public List<ContactAttemptDTO> findByRegister(Long registerId) {
        Register register = contactAttemptUtil.checkIfRegisterExists(registerId);
        return register.getContactAttempts().stream().map(x -> ContactAttempt.toDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public List<ContactAttemptDTO> findByPatientMr(Long mr) {
        Register register = contactAttemptUtil.checkIfRegisterExistsByMr(mr);
        return register.getContactAttempts().stream().map(x -> ContactAttempt.toDTO(x)).toList();
    }

    @Transactional
    public ResponseEntity<ContactAttemptDTO> create(NewSuccessContactAttemptDTO body, Long registerId) {
        Register register = contactAttemptUtil.checkIfRegisterExists(registerId);

        contactAttemptUtil.validateJson(body);

        Employee employee = entityManager.getReference(Employee.class, body.getEmployeeId());

        SuccessContactAttempt attempt = new SuccessContactAttempt(register, employee, body.getPhoneNumber(), body.getAttemptTime(),
            body.getPersonWhoAnswered(), body.getRelationship());
        contactAttemptRepository.save(attempt);
        return ResponseEntity.ok(ContactAttempt.toDTO(attempt));
    }

    @Transactional
    public ResponseEntity<ContactAttemptDTO> create(NewUnsuccessContactAttemptDTO body, Long registerId) {
        Register register = contactAttemptUtil.checkIfRegisterExists(registerId);

        contactAttemptUtil.validateJson(body);

        Employee employee = entityManager.getReference(Employee.class, body.getEmployeeId());

        UnsuccessContactAttempt attempt = new UnsuccessContactAttempt(register, employee, body.getPhoneNumber(), body.getAttemptTime(),
            body.getReasonForNotCalling());
        contactAttemptRepository.save(attempt);
        return ResponseEntity.ok(ContactAttempt.toDTO(attempt));

    }
}
