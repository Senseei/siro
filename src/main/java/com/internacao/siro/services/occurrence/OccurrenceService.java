package com.internacao.siro.services.occurrence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.occurrence.NewOccurrenceDTO;
import com.internacao.siro.dto.occurrence.OccurrenceDTO;
import com.internacao.siro.entities.Employee;
import com.internacao.siro.entities.Occurrence;
import com.internacao.siro.entities.Register;
import com.internacao.siro.repositories.OccurrenceRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class OccurrenceService {

    @PersistenceContext
    EntityManager entityManager;
    
    @Autowired
    OccurrenceRepository occurrenceRepository;
    @Autowired
    OccurrenceUtil occurrenceUtil;
    
    @Transactional(readOnly = true)
    public List<OccurrenceDTO> findAll() {
        List<Occurrence> result = occurrenceRepository.findAll();
        return result.stream().map(x -> OccurrenceDTO.of(x)).toList();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<OccurrenceDTO> findById(Long id) {
        Occurrence occurrence = occurrenceRepository.findById(id).orElse(null);
        if (occurrence == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(OccurrenceDTO.of(occurrence));
    }

    @Transactional(readOnly = true)
    public List<OccurrenceDTO> findByRegister(Long registerId) {
        Register register = occurrenceUtil.checkIfRegisterExists(registerId);
        return register.getOccurrences().stream().map(x -> OccurrenceDTO.of(x)).toList();
    }

    @Transactional(readOnly = true)
    public List<OccurrenceDTO> findByPatientMr(Long mr) {
        Register register = occurrenceUtil.checkIfRegisterExistsByMr(mr);
        return register.getOccurrences().stream().map(x -> OccurrenceDTO.of(x)).toList();
    }

    @Transactional
    public ResponseEntity<OccurrenceDTO> addToRegisterById(NewOccurrenceDTO body, Long registerId) {
        Register register = occurrenceUtil.checkIfRegisterExists(registerId);
        return create(body, register);
    }

    @Transactional
    public ResponseEntity<OccurrenceDTO> addToRegisterByMr(NewOccurrenceDTO body, Long mr) {
        Register register = occurrenceUtil.checkIfRegisterExistsByMr(mr);
        return create(body, register);
    }

    ResponseEntity<OccurrenceDTO> create(NewOccurrenceDTO body, Register register) {
        occurrenceUtil.validateJson(body);

        Employee employee = entityManager.getReference(Employee.class, body.getEmployeeId());

        Occurrence occurrence = new Occurrence(register, employee, body.getDescription());
        occurrenceRepository.save(occurrence);
        return ResponseEntity.ok(OccurrenceDTO.of(occurrence));
    }
}
