package com.internacao.siro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internacao.siro.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByMr(Long mr);
}
