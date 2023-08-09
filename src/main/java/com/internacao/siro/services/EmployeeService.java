package com.internacao.siro.services;

import java.time.LocalDateTime;
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
        List<Employee> result = employeeRepository.findByDeletedAtIsNull();
        List<EmployeeDTO> dto = result.stream().map(x -> new EmployeeDTO(x)).toList();
        return dto;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<EmployeeDTO> findById(Long id) {
        Employee result = employeeRepository.findByIdAndDeletedAtIsNull(id);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new EmployeeDTO(result));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<EmployeeDTO> findByRe(Long re) {
        Employee result = employeeRepository.findByReAndDeletedAtIsNull(re);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new EmployeeDTO(result));
    }

    @Transactional
    public ResponseEntity<EmployeeDTO> createNewEmployee(NewEmployeeDTO body) {
        Employee exists = employeeRepository.findByRe(body.getRe());
        if (exists != null && exists.getDeletedAt() != null) {
            exists.reverseDelete(body);
            employeeRepository.save(exists);
            return ResponseEntity.ok(new EmployeeDTO(exists));
        }
        else if (exists != null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Employee newEmployee = new Employee(body);
        employeeRepository.save(newEmployee);
        return ResponseEntity.ok(new EmployeeDTO(newEmployee));
    }

    @Transactional
    public ResponseEntity<EmployeeDTO> updateEmployee(Long id, UpdateEmployeeDTO body) {
        Employee employee = employeeRepository.findByIdAndDeletedAtIsNull(id);
        if (employee == null)
            return ResponseEntity.notFound().build();

        employee.updateEmployee(body);
        employeeRepository.save(employee);
        return ResponseEntity.ok(new EmployeeDTO(employee));
    }

    @Transactional
    public ResponseEntity<String> deleteEmployee(Long id) {
        Employee employee = employeeRepository.findByIdAndDeletedAtIsNull(id);
        if (employee == null)
            return ResponseEntity.notFound().build();
            
        employee.setDeletedAt(LocalDateTime.now());
        return ResponseEntity.noContent().build();
    }
}
