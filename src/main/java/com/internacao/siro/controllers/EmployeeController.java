package com.internacao.siro.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.employee.EmployeeDTO;
import com.internacao.siro.dto.employee.NewEmployeeDTO;
import com.internacao.siro.dto.employee.UpdateEmployeeDTO;
import com.internacao.siro.services.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> findAll(@RequestParam(required = false) Long re) {
        if (re != null) {
            EmployeeDTO dto = employeeService.findByRe(re).getBody();
            if (dto == null)
                return new ArrayList<EmployeeDTO>();
            return Collections.singletonList(dto);
        }
        return employeeService.findAll();
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody NewEmployeeDTO dto) {
        return employeeService.createNewEmployee(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findByPersonId(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody UpdateEmployeeDTO body) {
        return employeeService.updateEmployee(id, body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }
}
