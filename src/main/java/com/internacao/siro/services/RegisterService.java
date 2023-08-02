package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.RegisterDTO;
import com.internacao.siro.entities.Patient;
import com.internacao.siro.entities.Register;
import com.internacao.siro.repositories.PatientRepository;
import com.internacao.siro.repositories.RegisterRepository;

@Service
public class RegisterService {
    
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public List<RegisterDTO> findAll() {
        List<Register> result = registerRepository.findAll();
        List<RegisterDTO> dto = result.stream().map(x -> new RegisterDTO(x)).toList();
        return dto;
    }

    @Transactional(readOnly = true)
    public RegisterDTO findByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        Register register = registerRepository.findByPatient(patient);
        RegisterDTO dto = new RegisterDTO(register);
        return dto;
    }
}
