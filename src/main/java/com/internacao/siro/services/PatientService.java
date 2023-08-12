package com.internacao.siro.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.patient.NewPatientDTO;
import com.internacao.siro.dto.patient.PatientDTO;
import com.internacao.siro.dto.patient.UpdatePatientDTO;
import com.internacao.siro.entities.Patient;
import com.internacao.siro.repositories.PatientRepository;

@Service
public class PatientService {
    
    @Autowired
    PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public List<PatientDTO> findAll() {
        List<Patient> result = patientRepository.findByDeletedAtIsNull();
        List<PatientDTO> dto = result.stream().map(x -> new PatientDTO(x)).toList();
        return dto;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<PatientDTO> findById(Long id) {
        Patient result = patientRepository.findByIdAndDeletedAtIsNull(id);
        if (result == null)
            return ResponseEntity.notFound().build();
            
        return ResponseEntity.ok(new PatientDTO(result));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<PatientDTO> findByMr(Long mr) {
        Patient result = patientRepository.findByMrAndDeletedAtIsNull(mr);
        if (result == null)
            return ResponseEntity.notFound().build();
            
        return ResponseEntity.ok(new PatientDTO(result));
    }

    @Transactional
    public ResponseEntity<PatientDTO> createNewPatient(NewPatientDTO body) {
        Patient exists = patientRepository.findByMr(body.getMr());
        if (exists != null && exists.getDeletedAt() != null) {
            exists.reverseDelete(body);
            patientRepository.save(exists);
            return ResponseEntity.ok(new PatientDTO(exists));
        }
        else if (exists != null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Patient newPatient = new Patient(body);
        patientRepository.save(newPatient);
        return ResponseEntity.ok(new PatientDTO(newPatient));
    }

    @Transactional
    public ResponseEntity<PatientDTO> updatePatient(Long id, UpdatePatientDTO body) {
        Patient patient = patientRepository.findByIdAndDeletedAtIsNull(id);
        if (patient == null)
            return ResponseEntity.notFound().build();

        patient.updatePatient(body);
        patientRepository.save(patient);
        return ResponseEntity.ok(new PatientDTO(patient));
    }

    @Transactional
    public ResponseEntity<String> deletePatient(Long id) {
        Patient patient = patientRepository.findByIdAndDeletedAtIsNull(id);
        if (patient == null)
            return ResponseEntity.notFound().build();
            
        patient.setDeletedAt(LocalDateTime.now());
        return ResponseEntity.noContent().build();
    }
}
