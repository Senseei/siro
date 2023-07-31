package com.internacao.siro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.PatientDTO;
import com.internacao.siro.services.PatientService;

@RestController
@RequestMapping(value = {"/patients", "/patients/"})
public class PatientController {
    
    @Autowired
    PatientService patientService;

    @GetMapping
    public List<PatientDTO> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/search")
    public PatientDTO findByMR(@RequestParam Long mr) {
        PatientDTO patientDTO = patientService.findByMR(mr);
        return patientDTO;
    }
}
