package com.internacao.siro.services.contacctAttempt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.internacao.siro.validators.json.ContactAttemptJson;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

@Service
public class ContactAttemptService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ContactAttemptUtil contactAttemptUtil;
    @Autowired
    ContactAttemptRepository contactAttemptRepository;
    @Autowired
    ContactAttemptJson contactAttemptJson;

    @Transactional(readOnly = true)
    public List<ContactAttemptDTO> findAll() {
        List<ContactAttempt> result = contactAttemptRepository.findAll();
        return result.stream().map(x -> ContactAttempt.toDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public ContactAttemptDTO findById(Long id) {
        ContactAttempt attempt = contactAttemptRepository.findById(id).orElse(null);
        if (attempt == null)
            throw new EntityNotFoundException();
        return ContactAttempt.toDTO(attempt);
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
    public ContactAttemptDTO create(NewSuccessContactAttemptDTO body, Long registerId) {
        Register register = contactAttemptUtil.checkIfRegisterExists(registerId);

        contactAttemptJson.validate(body);

        Employee employee = entityManager.getReference(Employee.class, body.getEmployeeId());

        SuccessContactAttempt attempt = new SuccessContactAttempt(register, employee, body.getPhoneNumber(),
                body.getAttemptTime(),
                body.getPersonWhoAnswered(), body.getRelationship());
        contactAttemptRepository.save(attempt);
        return ContactAttempt.toDTO(attempt);
    }

    @Transactional
    public ContactAttemptDTO create(NewUnsuccessContactAttemptDTO body, Long registerId) {
        Register register = contactAttemptUtil.checkIfRegisterExists(registerId);

        contactAttemptJson.validate(body);

        Employee employee = entityManager.getReference(Employee.class, body.getEmployeeId());

        UnsuccessContactAttempt attempt = new UnsuccessContactAttempt(register, employee, body.getPhoneNumber(),
                body.getAttemptTime(),
                body.getReasonForNotCalling());
        contactAttemptRepository.save(attempt);
        return ContactAttempt.toDTO(attempt);

    }
}
