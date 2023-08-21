package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.clinic.ClinicDTO;
import com.internacao.siro.dto.clinic.NewClinicDTO;
import com.internacao.siro.entities.Clinic;
import com.internacao.siro.repositories.ClinicRepository;

@Service
public class ClinicService {
    
    @Autowired
    ClinicRepository clinicRepository;

    @Transactional(readOnly = true)
    public List<ClinicDTO> findAll() {
        List<Clinic> result = clinicRepository.findAll();
        return result.stream().map(x -> ClinicDTO.of(x)).toList();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ClinicDTO> findById(Long id) {
        Clinic clinic = clinicRepository.findById(id).orElse(null);
        if (clinic == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ClinicDTO.of(clinic));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ClinicDTO> findByName(String name) {
        Clinic clinic = clinicRepository.findByName(name);
        if (clinic == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ClinicDTO.of(clinic));
    }

    @Transactional
    public ResponseEntity<ClinicDTO> create(NewClinicDTO body) {
        if (body == null || body.getName() == null)
            throw new IllegalArgumentException("Body and its name cannot be null");
        if (clinicRepository.existsByName(body.getName()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Clinic clinic = new Clinic(body);
        clinicRepository.save(clinic);

        return ResponseEntity.ok(ClinicDTO.of(clinic));
    }
}
