package com.internacao.siro.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.employee.EmployeeDTO;
import com.internacao.siro.services.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> findAll(@RequestParam(required = false) Long re) {
        if (re != null) {
            EmployeeDTO dto = employeeService.findByRe(re);
            return Collections.singletonList(dto);
        }
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeDTO findByPersonId(@PathVariable Long id) {
        return employeeService.findById(id);
    }
}
