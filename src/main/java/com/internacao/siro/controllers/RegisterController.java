package com.internacao.siro.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.documentation.DocumentationDTO;
import com.internacao.siro.dto.documentation.NewDocumentationDTO;
import com.internacao.siro.dto.register.NewRegisterDTO;
import com.internacao.siro.dto.register.RegisterDTO;
import com.internacao.siro.dto.register.UpdateRegisterDTO;
import com.internacao.siro.services.DocumentationService;
import com.internacao.siro.services.register.RegisterService;

@RestController
@RequestMapping("/registers")
public class RegisterController {

    @Autowired
    RegisterService registerService;
    @Autowired
    DocumentationService documentationService;


    @GetMapping
    public List<RegisterDTO> findAll(@RequestParam(required = false) Long patientId,
        @RequestParam(required = false) Long mr) {
        Optional<ResponseEntity<RegisterDTO>> dto = Optional.empty();

        if (patientId != null) {
            dto = Optional.of(registerService.findByPatientId(patientId));
        } else if (mr != null) {
            dto = Optional.of(registerService.findByPatientMr(mr));
        }
    
        return dto.map(x -> {
            if (x.getStatusCode() == HttpStatus.OK)
                return Collections.singletonList(x.getBody());
            else
                return new ArrayList<RegisterDTO>();
        }).orElseGet(() -> registerService.findAll());
    }

    @PostMapping
    public ResponseEntity<RegisterDTO> create(@RequestBody NewRegisterDTO body) {
        return registerService.create(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegisterDTO> findById(@PathVariable Long id) {
        return registerService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegisterDTO> update(@PathVariable Long id, @RequestBody UpdateRegisterDTO body) {
        return registerService.update(id, body);
    }

    @GetMapping("/{id}/documentation")
    public List<DocumentationDTO> documentation(@PathVariable Long id) {
        return documentationService.findByRegister(id);
    }

    @PostMapping("/{id}/documentation")
    public ResponseEntity<DocumentationDTO> addDocumentation(@RequestBody NewDocumentationDTO body, @PathVariable Long id) {
        return documentationService.appendToRegisterById(body, id);
    }
}
