package com.internacao.siro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.DoctorDTO;
import com.internacao.siro.services.DoctorService;

@RestController
@RequestMapping(value = {"/doctors", "/doctors/"})
public class DoctorController {
    
    @Autowired
    DoctorService doctorService;

    @GetMapping
    public List<DoctorDTO> findAll() {
        return doctorService.findAll();
    }

    @GetMapping("/crm")
    public DoctorDTO findByCRM(@RequestParam Long crm) {
        return doctorService.findByCRM(crm);
    }

    @GetMapping("/id")
    public DoctorDTO findByPersonId(@RequestParam Long id) {
        return doctorService.findByPersonId(id);
    }
}
