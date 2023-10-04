package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.patient.NewPatientDTO;
import com.internacao.siro.dto.patient.PatientDTO;
import com.internacao.siro.dto.patient.UpdatePatientDTO;
import com.internacao.siro.dto.relative.RelativeDTO;
import com.internacao.siro.entities.Patient;
import com.internacao.siro.entities.Relative;
import com.internacao.siro.repositories.PatientRepository;
import com.internacao.siro.repositories.RelativeRepository;
import com.internacao.siro.validators.PatientJson;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    RelativeRepository relativeRepository;
    @Autowired
    PatientJson patientJson;

    @Transactional(readOnly = true)
    public List<PatientDTO> findAll() {
        List<Patient> result = patientRepository.findAll();
        return result.stream().map(x -> PatientDTO.of(x)).toList();
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

    @Transactional(readOnly = true)
    public List<RelativeDTO> relatives(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null)
            throw new EntityNotFoundException("The patient with the given Id does not exist");

        List<Relative> result = relativeRepository.findByPatient(patient);
        return result.stream().map(x -> RelativeDTO.of(x)).toList();
    }

    @Transactional
    public ResponseEntity<PatientDTO> create(NewPatientDTO body) {
        if (patientRepository.existsByMr(body.getMr()))
            throw new DuplicateKeyException("There is already a patient with the given medical record");

        patientJson.validate(body);

        Patient newPatient = new Patient(body);
        patientRepository.save(newPatient);
        return ResponseEntity.ok(PatientDTO.of(newPatient));
    }

    @Transactional
    public ResponseEntity<PatientDTO> update(Long id, UpdatePatientDTO body) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null)
            return ResponseEntity.notFound().build();

        patientJson.validate(body);

        patient.update(body);
        patientRepository.save(patient);
        return ResponseEntity.ok(PatientDTO.of(patient));
    }
}
