package com.internacao.siro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.RegisterDTO;
import com.internacao.siro.services.RegisterService;

@RestController
@RequestMapping(value = {"/registers", "/registers/"})
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @GetMapping
    public List<RegisterDTO> findAll() {
        return registerService.findAll();
    }

    @GetMapping(value = "/patientId")
    public RegisterDTO findByPatientId(@RequestParam Long patientId) {
        return registerService.findByPatientId(patientId);
    }
}
