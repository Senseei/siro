package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.patient.PatientDTO;
import com.internacao.siro.entities.Patient;
import com.internacao.siro.repositories.PatientRepository;

@Service
public class PatientService {
    
    @Autowired
    PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public List<PatientDTO> findAll() {
        List<Patient> result = patientRepository.findAll();
        List<PatientDTO> dto = result.stream().map(x -> new PatientDTO(x)).toList();
        return dto;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<PatientDTO> findById(Long id) {
        Patient result = patientRepository.findById(id).orElse(null);
        if (result == null)
            return ResponseEntity.notFound().build();
            
        return ResponseEntity.ok(new PatientDTO(result));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<PatientDTO> findByMr(Long mr) {
        Patient result = patientRepository.findByMr(mr);
        if (result == null)
            return ResponseEntity.notFound().build();
            
        return ResponseEntity.ok(new PatientDTO(result));
    }
}
