package com.internacao.siro.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.clinic.ClinicDTO;
import com.internacao.siro.dto.clinic.NewClinicDTO;
import com.internacao.siro.services.ClinicService;

@RestController
@RequestMapping("/clinics")
public class ClinicController {
    
    @Autowired
    ClinicService clinicService;

    @GetMapping
    public List<ClinicDTO> findAll(@RequestParam(required = false) String name) {
        if (name != null) {
            ClinicDTO clinic = clinicService.findByName(name).getBody();
            if (clinic == null)
                return new ArrayList<>();
            return Collections.singletonList(clinic);
        }
        return clinicService.findAll();
    }

    @PostMapping
    public ResponseEntity<ClinicDTO> create(@RequestBody NewClinicDTO body) {
        return clinicService.create(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicDTO> findById(@PathVariable Long id) {
        return clinicService.findById(id);
    }
}
