package com.internacao.siro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internacao.siro.entities.Clinic;


public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    Clinic findByName(String name);
    Boolean existsByName(String name);
}
