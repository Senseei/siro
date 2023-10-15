package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.employee.EmployeeDTO;
import com.internacao.siro.dto.employee.NewEmployeeDTO;
import com.internacao.siro.dto.employee.UpdateEmployeeDTO;
import com.internacao.siro.entities.Employee;
import com.internacao.siro.repositories.EmployeeRepository;
import com.internacao.siro.validators.json.EmployeeJson;

import jakarta.persistence.EntityNotFoundException;

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
    public EmployeeDTO findById(Long id) {
        Employee result = employeeRepository.findById(id).orElse(null);
        if (result == null)
            throw new EntityNotFoundException();
        return EmployeeDTO.of(result);
    }

    @Transactional(readOnly = true)
    public EmployeeDTO findByRe(Long re) {
        Employee result = employeeRepository.findByRe(re);
        if (result == null)
            throw new EntityNotFoundException();
        return EmployeeDTO.of(result);
    }

    @Transactional
    public EmployeeDTO create(NewEmployeeDTO body) {
        if (employeeRepository.existsByRe(body.getRe()))
            throw new DuplicateKeyException("There is already an employee with this RE");

        employeeJson.validate(body);

        Employee newEmployee = new Employee(body);
        employeeRepository.save(newEmployee);
        return EmployeeDTO.of(newEmployee);
    }

    @Transactional
    public EmployeeDTO update(Long id, UpdateEmployeeDTO body) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null)
            throw new EntityNotFoundException();

        employeeJson.validate(body);

        employee.update(body);
        employeeRepository.save(employee);
        return EmployeeDTO.of(employee);
    }
}
