package com.internacao.siro.services;

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
        List<Patient> result = patientRepository.findAll();
        List<PatientDTO> dto = result.stream().map(x -> PatientDTO.of(x)).toList();
        return dto;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<PatientDTO> findById(Long id) {
        Patient result = patientRepository.findById(id).orElse(null);
        if (result == null)
            return ResponseEntity.notFound().build();
            
        return ResponseEntity.ok(PatientDTO.of(result));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<PatientDTO> findByMr(Long mr) {
        Patient result = patientRepository.findByMr(mr);
        if (result == null)
            return ResponseEntity.notFound().build();
            
        return ResponseEntity.ok(PatientDTO.of(result));
    }

    @Transactional
    public ResponseEntity<PatientDTO> create(NewPatientDTO body) {
        if (patientRepository.existsByMr(body.getMr()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Patient newPatient = new Patient(body);
        patientRepository.save(newPatient);
        return ResponseEntity.ok(PatientDTO.of(newPatient));
    }

    @Transactional
    public ResponseEntity<PatientDTO> update(Long id, UpdatePatientDTO body) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null)
            return ResponseEntity.notFound().build();

        patient.update(body);
        patientRepository.save(patient);
        return ResponseEntity.ok(PatientDTO.of(patient));
    }
}
