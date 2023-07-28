package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.PatientDTO;
import com.internacao.siro.entities.Patient;
import com.internacao.siro.projections.PatientProjection;
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
    public PatientDTO findByPersonId(Long personId) {

        PatientProjection result = patientRepository.findByPersonId(personId);
        
        if (result == null) {
            return new PatientDTO();
        }
        return new PatientDTO(result);
    }

    @Transactional(readOnly = true)
    public PatientDTO findByMR(Long mr) {

        PatientProjection result = patientRepository.findByMR(mr);

        if (result == null) {
            return new PatientDTO();
        }
        return new PatientDTO(result);
    }
}
