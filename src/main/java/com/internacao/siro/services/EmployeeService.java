package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.employee.EmployeeDTO;
import com.internacao.siro.dto.employee.NewEmployeeDTO;
import com.internacao.siro.dto.employee.UpdateEmployeeDTO;
import com.internacao.siro.entities.Employee;
import com.internacao.siro.repositories.EmployeeRepository;
import com.internacao.siro.validators.json.EmployeeJson;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeJson employeeJson;

    @Transactional(readOnly = true)
    public List<EmployeeDTO> findAll() {
        List<Employee> result = employeeRepository.findAll();
        return result.stream().map(x -> EmployeeDTO.of(x)).toList();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<EmployeeDTO> findById(Long id) {
        Employee result = employeeRepository.findById(id).orElse(null);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(EmployeeDTO.of(result));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<EmployeeDTO> findByRe(Long re) {
        Employee result = employeeRepository.findByRe(re);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(EmployeeDTO.of(result));
    }

    @Transactional
    public ResponseEntity<EmployeeDTO> create(NewEmployeeDTO body) {
        if (employeeRepository.existsByRe(body.getRe()))
            throw new DuplicateKeyException("There is already an employee with this RE");

        employeeJson.validate(body);

        Employee newEmployee = new Employee(body);
        employeeRepository.save(newEmployee);
        return ResponseEntity.ok(EmployeeDTO.of(newEmployee));
    }

    @Transactional
    public ResponseEntity<EmployeeDTO> update(Long id, UpdateEmployeeDTO body) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null)
            return ResponseEntity.notFound().build();

        employeeJson.validate(body);

        employee.update(body);
        employeeRepository.save(employee);
        return ResponseEntity.ok(EmployeeDTO.of(employee));
    }
}
