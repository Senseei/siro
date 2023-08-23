package com.internacao.siro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.internacao.siro.entities.Patient;
import com.internacao.siro.entities.Relative;
import com.internacao.siro.entities.RelativeId;


public interface RelativeRepository extends JpaRepository<Relative, RelativeId> {

    @Query("SELECT r FROM Relative r WHERE id.patient = :patient")
    List<Relative> findByPatient(@Param("patient") Patient patient);
}
