package com.internacao.siro.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.patient.PatientDTO;
import com.internacao.siro.services.PatientService;

@RestController
@RequestMapping(value = {"/patients", "/patients/"})
public class PatientController {
    
    @Autowired
    PatientService patientService;

    @GetMapping
    public List<PatientDTO> findAll(@RequestParam(required = false) Long mr) {
        if (mr != null) {
            PatientDTO dto = patientService.findByMr(mr).getBody();
            return Collections.singletonList(dto);
        }
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findByPersonId(@PathVariable Long id) {
        return patientService.findById(id);
    }
}
