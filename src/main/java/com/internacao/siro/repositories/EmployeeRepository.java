package com.internacao.siro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.internacao.siro.entities.Employee;
import com.internacao.siro.projections.EmployeeProjection;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    @Query(nativeQuery = true, value = """
            SELECT tb_people.id AS personId, tb_people.name, tb_people.birthday,
            tb_employees.re
            FROM tb_people
            INNER JOIN tb_employees ON tb_people.id = tb_employees.person_id
            WHERE tb_employees.person_id = :personId
            """)
    EmployeeProjection findByPersonId(Long personId);

    @Query(nativeQuery = true, value = """
            SELECT tb_people.id AS personId, tb_people.name, tb_people.birthday,
            tb_employees.re
            FROM tb_people
            INNER JOIN tb_employees ON tb_people.id = tb_employees.person_id
            WHERE tb_employees.re = :re
            """)
    EmployeeProjection findByRE(Long re);
}
