package com.internacao.siro.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.internacao.siro.entities.Occurrence;
import com.internacao.siro.entities.Register;

public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {
    
    List<Occurrence> findByRegister(Register register);

    @Modifying
    @Query(nativeQuery = true, value = """
            INSERT INTO tb_documentation_occurrences (documentation_id, occurrence_id, date)
            VALUES (:documentationId, :occurrenceId, :date)
            """)
    void createDocumentationOccurrence(Long documentationId, Long occurrenceId, LocalDateTime date);
}
