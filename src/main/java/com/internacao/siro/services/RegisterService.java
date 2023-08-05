package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.RegisterDTO;
import com.internacao.siro.entities.Patient;
import com.internacao.siro.entities.Register;
import com.internacao.siro.projections.RelativeProjection;
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
        List<RegisterDTO> dto = result.stream().map(x -> createRegisterDTO(x)).toList();
        return dto;
    }

    @Transactional(readOnly = true)
    public RegisterDTO findById(Long id) {
        Register result = registerRepository.findById(id).orElse(null);
        return createRegisterDTO(result);
    }

    @Transactional(readOnly = true)
    public RegisterDTO findByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        return createRegisterDTO(patient);
    }

    @Transactional(readOnly = true)
    public RegisterDTO findByPatientMr(Long mr) {
        Patient patient = patientRepository.findByMr(mr);
        return createRegisterDTO(patient);
    }

    private RegisterDTO createRegisterDTO(Register register) {
        if (register != null && register.getRelative() != null) {
                RelativeProjection relativeProjection = patientRepository.findRelativeById(
                    register.getRelative().getId(), register.getPatient().getId());
                return new RegisterDTO(register, relativeProjection);
            }
            return new RegisterDTO(register);
    }

    private RegisterDTO createRegisterDTO(Patient patient) {
        Register register = registerRepository.findByPatient(patient);
        if (register != null && register.getRelative() != null) {
            RelativeProjection relativeProjection = patientRepository.findRelativeById(register.getRelative().getId(), patient.getId());
            return new RegisterDTO(register, relativeProjection);
        }
        return new RegisterDTO(register);
    }
}
