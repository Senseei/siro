package com.internacao.siro.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.patient.NewPatientDTO;
import com.internacao.siro.dto.patient.PatientDTO;
import com.internacao.siro.dto.patient.UpdatePatientDTO;
import com.internacao.siro.dto.relative.RelativeDTO;
import com.internacao.siro.services.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping
    public List<PatientDTO> findAll(@RequestParam(required = false) Long mr) {
        if (mr != null) {
            PatientDTO dto = patientService.findByMr(mr);
            if (dto == null)
                return new ArrayList<>();
            return Collections.singletonList(dto);
        }
        return patientService.findAll();
    }

    @PostMapping
    public ResponseEntity<PatientDTO> create(@RequestBody NewPatientDTO body) {
        return ResponseEntity.ok(patientService.create(body));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findByPersonId(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@PathVariable Long id, @RequestBody UpdatePatientDTO body) {
        return ResponseEntity.ok(patientService.update(id, body));
    }

    @GetMapping("/{id}/relatives")
    public List<RelativeDTO> relatives(@PathVariable Long id) {
        return patientService.relatives(id);
    }
}
