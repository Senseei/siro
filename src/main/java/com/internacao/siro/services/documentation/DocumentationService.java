package com.internacao.siro.services.documentation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.documentation.CancelDocumentationDTO;
import com.internacao.siro.dto.documentation.DocumentationDTO;
import com.internacao.siro.dto.documentation.NewDocumentationDTO;
import com.internacao.siro.dto.documentation.UpdateDocumentationDTO;
import com.internacao.siro.entities.Doctor;
import com.internacao.siro.entities.Documentation;
import com.internacao.siro.entities.Employee;
import com.internacao.siro.entities.Occurrence;
import com.internacao.siro.entities.Register;
import com.internacao.siro.repositories.DocumentationRepository;
import com.internacao.siro.repositories.OccurrenceRepository;
import com.internacao.siro.repositories.RegisterRepository;
import com.internacao.siro.validators.json.DocumentationJson;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

@Service
public class DocumentationService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    DocumentationUtil documentationUtil;
    @Autowired
    DocumentationRepository documentationRepository;
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    OccurrenceRepository occurrenceRepository;
    @Autowired
    DocumentationJson documentationJson;

    @Transactional(readOnly = true)
    public List<DocumentationDTO> findAll() {
        List<Documentation> result = documentationRepository.findAll();
        return result.stream().map(x -> new DocumentationDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public DocumentationDTO findById(Long id) {
        Documentation doc = documentationRepository.findById(id).orElse(null);
        if (doc == null)
            throw new EntityNotFoundException();
        return DocumentationDTO.of(doc);
    }

    @Transactional(readOnly = true)
    public List<DocumentationDTO> findByRegister(Long registerId) {
        Register register = documentationUtil.checkIfRegisterExists(registerId);
        ;
        return register.getDocumentation().stream().map(x -> DocumentationDTO.of(x)).toList();
    }

    @Transactional(readOnly = true)
    public List<DocumentationDTO> findByPatientMr(Long mr) {
        Register register = documentationUtil.checkIfRegisterExistsByMr(mr);
        return register.getDocumentation().stream().map(x -> DocumentationDTO.of(x)).toList();
    }

    @Transactional
    public DocumentationDTO update(UpdateDocumentationDTO body, Long id) {
        Documentation documentation = documentationRepository.findById(id).orElse(null);
        if (documentation == null)
            throw new EntityNotFoundException();

        Doctor doctor = null;
        if (body.getDoctorId() != null)
            doctor = entityManager.getReference(Doctor.class, body.getDoctorId());

        documentation.update(body.getDoc(), doctor);
        documentationRepository.save(documentation);
        return DocumentationDTO.of(documentation);
    }

    @Transactional
    public ResponseEntity<String> cancel(CancelDocumentationDTO body, Long id) {
        Documentation documentation = documentationRepository.findById(id).orElse(null);
        if (documentation == null)
            throw new EntityNotFoundException();

        documentationJson.validate(body);

        Register register = entityManager.getReference(Register.class, documentation.getRegister().getId());
        Employee employee = entityManager.getReference(Employee.class, body.getEmployeeId());

        documentation.cancel();
        Occurrence occurrence = new Occurrence(register, employee, body.getCause());
        occurrenceRepository.save(occurrence);
        documentationRepository.save(documentation);

        occurrenceRepository.createDocumentationOccurrence(documentation.getId(), occurrence.getId(),
                LocalDateTime.now());

        return ResponseEntity.noContent().build();
    }

    @Transactional
    public DocumentationDTO appendToRegisterById(NewDocumentationDTO body, Long registerId) {
        Register register = documentationUtil.checkIfRegisterExists(registerId);
        return create(body, register);
    }

    @Transactional
    public DocumentationDTO appendToRegisterByPatientMr(NewDocumentationDTO body, Long mr) {
        Register register = documentationUtil.checkIfRegisterExistsByMr(mr);
        return create(body, register);
    }

    DocumentationDTO create(NewDocumentationDTO body, Register register) {
        documentationJson.validate(body);

        Doctor doctor = entityManager.getReference(Doctor.class, body.getDoctorId());

        Documentation documentation = new Documentation(register, body.getDoc(), doctor);
        documentationRepository.save(documentation);

        return DocumentationDTO.of(documentation);
    }
}
