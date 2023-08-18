package com.internacao.siro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internacao.siro.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByRe(Long re);
    Boolean existsByRe(Long re);
}
