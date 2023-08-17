package com.internacao.siro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internacao.siro.entities.Patient;
import com.internacao.siro.entities.Register;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    
    Register findByPatient(Patient patient);
    Register findByPatientId(Long id);
    Boolean existsByPatientId(Long id);
}
