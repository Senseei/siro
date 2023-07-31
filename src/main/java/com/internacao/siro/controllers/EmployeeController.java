package com.internacao.siro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.EmployeeDTO;
import com.internacao.siro.services.EmployeeService;

@RestController
@RequestMapping(value = {"/employees", "/employees/"})
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/search")
    public EmployeeDTO findByRE(@RequestParam Long re) {
        return employeeService.findByRE(re);
    }
}
