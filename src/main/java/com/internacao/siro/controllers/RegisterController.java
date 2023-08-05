package com.internacao.siro.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.RegisterDTO;
import com.internacao.siro.services.RegisterService;

@RestController
@RequestMapping("/registers")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @GetMapping
    public List<RegisterDTO> findAll(@RequestParam(required = false) Long patientId,
        @RequestParam(required = false) Long mr) {
        Optional<RegisterDTO> dto = Optional.empty();

        if (patientId != null) {
            dto = Optional.of(registerService.findByPatientId(patientId));
        } else if (mr != null) {
            dto = Optional.of(registerService.findByPatientMr(mr));
        }
    
        return dto.map(x -> Collections.singletonList(x)).orElseGet(() -> registerService.findAll());
    }

    @GetMapping("/{id}")
    public RegisterDTO findById(@PathVariable Long id) {
        return registerService.findById(id);
    }
}
