package com.internacao.siro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internacao.siro.entities.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
    Doctor findByCrm(Long crm);
}
