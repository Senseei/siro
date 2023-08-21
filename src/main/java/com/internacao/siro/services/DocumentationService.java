package com.internacao.siro.services;

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
import com.internacao.siro.entities.Patient;
import com.internacao.siro.entities.Register;
import com.internacao.siro.repositories.DoctorRepository;
import com.internacao.siro.repositories.DocumentationRepository;
import com.internacao.siro.repositories.EmployeeRepository;
import com.internacao.siro.repositories.OccurrenceRepository;
import com.internacao.siro.repositories.PatientRepository;
import com.internacao.siro.repositories.RegisterRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

@Service
public class DocumentationService {
    
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    DocumentationRepository documentationRepository;
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    OccurrenceRepository occurrenceRepository;


    @Transactional(readOnly = true)
    public List<DocumentationDTO> findAll() {
        List<Documentation> result = documentationRepository.findAll();
        List<DocumentationDTO> dto = result.stream().map(x -> new DocumentationDTO(x)).toList();
        return dto;
    }

    @Transactional(readOnly = true)
    public List<DocumentationDTO> findByRegister(Long registerId) {
        Register register = registerRepository.findById(registerId).orElse(null);
        
        if (register == null)
            throw new EntityNotFoundException("The register with the given Id does not exist");

        List<Documentation> docs = register.getDocumentation();
        return docs.stream().map(x -> DocumentationDTO.of(x)).toList();
    }

    @Transactional(readOnly = true)
    public List<DocumentationDTO> findByPatientMr(Long mr) {
        Register register = checkForRegisterByMr(mr);

        List<Documentation> docs = register.getDocumentation();
        return docs.stream().map(x -> DocumentationDTO.of(x)).toList();
    }

    @Transactional
    public ResponseEntity<DocumentationDTO> update(UpdateDocumentationDTO body, Long id) {
        Documentation documentation = documentationRepository.findById(id).orElse(null);
        if (documentation == null)
            return ResponseEntity.notFound().build();

        Doctor doctor = null;
        if (body.getDoctorId() != null)
            doctor = entityManager.getReference(Doctor.class, body.getDoctorId());
        
        documentation.update(body.getDoc(), doctor);
        documentationRepository.save(documentation);
        return ResponseEntity.ok(DocumentationDTO.of(documentation));
    }

    @Transactional
    public ResponseEntity<String> cancel(CancelDocumentationDTO body, Long id) {
        Documentation documentation = documentationRepository.findById(id).orElse(null);
        if (documentation == null)
            return ResponseEntity.notFound().build();
        if (body.getEmployeeId() == null || body.getCause() == null)
            return ResponseEntity.badRequest().body("Body and its properties cannot be null");
        if (!employeeRepository.existsById(body.getEmployeeId()))
            throw new EntityNotFoundException("The employee with the given Id does not exist");

        Register register = entityManager.getReference(Register.class, documentation.getRegister().getId());
        Employee employee = entityManager.getReference(Employee.class, body.getEmployeeId());


        documentation.cancel();
        Occurrence occurrence = new Occurrence(register, employee, body.getCause());
        occurrenceRepository.save(occurrence);
        documentationRepository.save(documentation);

        occurrenceRepository.createDocumentationOccurrence(documentation.getId(), occurrence.getId(), LocalDateTime.now());

        return ResponseEntity.noContent().build();
    }

    @Transactional
    public ResponseEntity<DocumentationDTO> appendToRegisterById(NewDocumentationDTO body, Long registerId) {
        Register register = registerRepository.findById(registerId).orElse(null);

        if (register == null)
            throw new EntityNotFoundException("The register with the given Id does not exist");
        if (body == null || body.getDoc() == null || body.getDoctorId() == null)
            throw new IllegalArgumentException("body and its field cannot be null");
        if (!doctorRepository.existsById(body.getDoctorId()))
            throw new EntityNotFoundException("The doctor with the given Id does not exist");

        Doctor doctor = entityManager.getReference(Doctor.class, body.getDoctorId());
        
        Documentation documentation = new Documentation(body.getDoc(), doctor);
        documentation.setRegister(register);
        documentationRepository.save(documentation);

        return ResponseEntity.ok(DocumentationDTO.of(documentation));
    }

    @Transactional
    public ResponseEntity<DocumentationDTO> appendToRegisterByPatientMr(NewDocumentationDTO body, Long mr) {
        Register register = checkForRegisterByMr(mr);
        if (body.getDoc() == null)
            throw new IllegalArgumentException("doc number cannot be null");
        if (body.getDoctorId() == null || !doctorRepository.existsById(body.getDoctorId()))
            throw new EntityNotFoundException("The doctor with the given Id does not exist");

        Doctor doctor = entityManager.getReference(Doctor.class, body.getDoctorId());
        
        Documentation documentation = new Documentation(body.getDoc(), doctor);
        documentation.setRegister(register);
        documentationRepository.save(documentation);

        return ResponseEntity.ok(DocumentationDTO.of(documentation));
    }

    public Register checkForRegisterByMr(Long mr) {
        Patient patient = patientRepository.findByMr(mr);
        if (patient == null)
            throw new EntityNotFoundException("The patient with the given medical record does not exist");

        Register register = registerRepository.findByPatient(patient);
        if (register == null)
            throw new EntityNotFoundException("There is no register with this patient yet");

        return register;
    }
}
