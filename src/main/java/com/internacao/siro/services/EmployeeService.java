package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.EmployeeDTO;
import com.internacao.siro.entities.Employee;
import com.internacao.siro.projections.EmployeeProjection;
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
    public EmployeeDTO findByPersonId(Long personId) {
        EmployeeProjection result = employeeRepository.findByPersonId(personId);
        return new EmployeeDTO(result);
    }

    @Transactional(readOnly = true)
    public EmployeeDTO findByRE(Long re) {
        EmployeeProjection result = employeeRepository.findByRE(re);
        return new EmployeeDTO(result);
    }
}
