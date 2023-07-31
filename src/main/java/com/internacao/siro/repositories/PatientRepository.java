package com.internacao.siro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.internacao.siro.entities.Patient;
import com.internacao.siro.projections.PatientProjection;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_people.id, tb_people.name, tb_people.birthday, tb_patients.mr,
            FROM tb_people
            INNER JOIN tb_patients ON tb_people.id = tb_patients.person_id
            WHERE tb_patients.mr = :mr
            """)
    PatientProjection findByMR(Long mr);
}
