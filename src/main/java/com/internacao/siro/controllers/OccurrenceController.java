package com.internacao.siro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.occurrence.OccurrenceDTO;
import com.internacao.siro.services.occurrence.OccurrenceService;

@RestController
@RequestMapping("/occurrences")
public class OccurrenceController {
    
    @Autowired
    OccurrenceService occurrenceService;

    @GetMapping
    public List<OccurrenceDTO> findAll(@RequestParam(required = false) Long mr) {
        if (mr != null)
            return occurrenceService.findByPatientMr(mr);
        return occurrenceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OccurrenceDTO> findById(@PathVariable Long id) {
        return occurrenceService.findById(id);
    }
}
