package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.employee.EmployeeDTO;
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
    public EmployeeDTO findById(Long id) {
        Employee result = employeeRepository.findById(id).orElse(null);
        return new EmployeeDTO(result);
    }

    @Transactional(readOnly = true)
    public EmployeeDTO findByRe(Long re) {
        Employee result = employeeRepository.findByRe(re);
        return new EmployeeDTO(result);
    }
}
