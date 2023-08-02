package com.internacao.siro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internacao.siro.entities.Occurrence;
import com.internacao.siro.entities.Register;

public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {
    
    List<Occurrence> findByRegister(Register register);
}
