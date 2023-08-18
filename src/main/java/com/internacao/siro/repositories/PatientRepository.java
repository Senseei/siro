package com.internacao.siro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.internacao.siro.entities.Patient;
import com.internacao.siro.projections.RelativeProjection;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_people.id, name, birthday, relationship
            FROM tb_people
            INNER JOIN tb_relatives ON tb_relatives.relative_id = tb_people.id
            WHERE tb_relatives.relative_id = :relativeId AND tb_relatives.patient_id = :patientId
            """)
    RelativeProjection findRelativeById(Long relativeId, Long patientId);

    @Modifying
    @Query(nativeQuery = true, value = """
            DELETE FROM tb_relatives
            WHERE relative_id = :relativeId AND patient_id = :patientId
            """)
    void deleteRelative(Long relativeId, Long patientId);

    @Modifying
    @Query(nativeQuery = true, value = """
            INSERT INTO tb_relatives (relative_id, patient_id, relationship)
            VALUES (:relativeId, :patientId, :relationship)
            """)
    void addRelative(Long relativeId, Long patientId, String relationship);

    Patient findByMr(Long mr);
    Boolean existsByMr(Long mr);
}
