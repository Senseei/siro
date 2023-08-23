package com.internacao.siro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.contactAttempt.ContactAttemptDTO;
import com.internacao.siro.services.contacctAttempt.ContactAttemptService;

@RestController
@RequestMapping("/contactattempts")
public class ContactAttemptController {
    
    @Autowired
    ContactAttemptService contactAttemptService;

    @GetMapping
    public List<ContactAttemptDTO> findAll(@RequestParam(required = false) Long mr) {
        if (mr != null)
            return contactAttemptService.findByPatientMr(mr);
        return contactAttemptService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactAttemptDTO> findById(@PathVariable Long id) {
        return contactAttemptService.findById(id);
    }
}
