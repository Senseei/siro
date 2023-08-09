package com.internacao.siro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RegisterDTO> findById(Long id) {
        Register result = registerRepository.findById(id).orElse(null);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(createRegisterDTO(result));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<RegisterDTO> findByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        RegisterDTO register = createRegisterDTO(patient);
        if (register == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(register);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<RegisterDTO> findByPatientMr(Long mr) {
        Patient patient = patientRepository.findByMr(mr);
        RegisterDTO register = createRegisterDTO(patient);
        if (register == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(register);
    }

    private RegisterDTO createRegisterDTO(Register register) {
        if (register.getRelative() != null) {
                RelativeProjection relativeProjection = patientRepository.findRelativeById(
                    register.getRelative().getId(), register.getPatient().getId());
                return new RegisterDTO(register, relativeProjection);
            }
            return new RegisterDTO(register);
    }

    private RegisterDTO createRegisterDTO(Patient patient) {
        Optional<Register> registerOpt = Optional.ofNullable(registerRepository.findByPatient(patient));

        return registerOpt.map(register -> {
            RelativeProjection relativeProjection = null;
            if (register.getRelative() != null) {
                relativeProjection = patientRepository.findRelativeById(register.getRelative().getId(), patient.getId());
            }
            return new RegisterDTO(register, relativeProjection);
        }).orElse(null);
    }
}
