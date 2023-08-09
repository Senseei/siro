package com.internacao.siro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internacao.siro.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDeletedAtIsNull();
    Employee findByIdAndDeletedAtIsNull(Long id);
    Employee findByReAndDeletedAtIsNull(Long re);
    Employee findByRe(Long re);
}
