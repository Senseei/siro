package com.internacao.siro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.internacao.siro.entities.Doctor;
import com.internacao.siro.projections.DoctorProjection;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_people.id, tb_people.name, tb_birthday, tb_doctors.crm
            FROM tb_people
            INNER JOIN tb_doctors ON tb_people.id = tb_doctors.person_id
            WHERE tb_doctors.crm = :crm
            """)
    DoctorProjection findByCRM(Long crm);
}
