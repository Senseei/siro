package com.internacao.siro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internacao.siro.entities.Documentation;
import com.internacao.siro.entities.Register;

public interface DocumentationRepository extends JpaRepository<Documentation, Long> {
    
    List<Documentation> findByRegister(Register register);
}
