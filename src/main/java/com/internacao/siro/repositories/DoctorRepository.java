package com.internacao.siro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internacao.siro.entities.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
    List<Doctor> findByDeletedAtIsNull();
    Doctor findByIdAndDeletedAtIsNull(Long id);
    Doctor findByCrmAndDeletedAtIsNull(Long crm);
    Doctor findByCrm(Long crm);
}
