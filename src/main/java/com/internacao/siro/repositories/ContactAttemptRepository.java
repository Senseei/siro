package com.internacao.siro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internacao.siro.entities.Register;
import com.internacao.siro.entities.contactAttempt.ContactAttempt;

public interface ContactAttemptRepository extends JpaRepository<ContactAttempt, Long> {
    List<ContactAttempt> findByRegister(Register register);
}
