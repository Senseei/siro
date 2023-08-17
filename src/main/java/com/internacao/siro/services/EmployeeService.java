package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.employee.EmployeeDTO;
import com.internacao.siro.dto.employee.NewEmployeeDTO;
import com.internacao.siro.dto.employee.UpdateEmployeeDTO;
import com.internacao.siro.entities.Employee;
import com.internacao.siro.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<EmployeeDTO> findAll() {
        List<Employee> result = employeeRepository.findAll();
        List<EmployeeDTO> dto = result.stream().map(x -> new EmployeeDTO(x)).toList();
        return dto;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<EmployeeDTO> findById(Long id) {
        Employee result = employeeRepository.findById(id).orElse(null);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new EmployeeDTO(result));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<EmployeeDTO> findByRe(Long re) {
        Employee result = employeeRepository.findByRe(re);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new EmployeeDTO(result));
    }

    @Transactional
    public ResponseEntity<EmployeeDTO> create(NewEmployeeDTO body) {
        if (employeeRepository.exexistsByRe(body.getRe()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Employee newEmployee = new Employee(body);
        employeeRepository.save(newEmployee);
        return ResponseEntity.ok(new EmployeeDTO(newEmployee));
    }

    @Transactional
    public ResponseEntity<EmployeeDTO> update(Long id, UpdateEmployeeDTO body) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null)
            return ResponseEntity.notFound().build();

        employee.update(body);
        employeeRepository.save(employee);
        return ResponseEntity.ok(new EmployeeDTO(employee));
    }
}
