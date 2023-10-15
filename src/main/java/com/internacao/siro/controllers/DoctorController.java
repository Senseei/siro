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

import com.internacao.siro.dto.doctor.DoctorDTO;
import com.internacao.siro.dto.doctor.NewDoctorDTO;
import com.internacao.siro.dto.doctor.UpdateDoctorDTO;
import com.internacao.siro.services.DoctorService;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping
    public List<DoctorDTO> findAll(@RequestParam(required = false) Long crm) {
        if (crm != null) {
            DoctorDTO dto = doctorService.findByCrm(crm);
            if (dto == null)
                return new ArrayList<DoctorDTO>();
            return Collections.singletonList(dto);
        }
        return doctorService.findAll();
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> create(@RequestBody NewDoctorDTO dto) {
        return ResponseEntity.ok(doctorService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> findByPersonId(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> update(@PathVariable Long id, @RequestBody UpdateDoctorDTO body) {
        return ResponseEntity.ok(doctorService.update(id, body));
    }
}
