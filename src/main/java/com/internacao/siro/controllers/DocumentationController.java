package com.internacao.siro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.documentation.DocumentationDTO;
import com.internacao.siro.dto.documentation.NewDocumentationDTO;
import com.internacao.siro.services.DocumentationService;

@RestController
@RequestMapping("/documentation")
public class DocumentationController {

    @Autowired
    DocumentationService documentationService;
    
    @GetMapping
    public List<DocumentationDTO> findAll(@RequestParam(required = false) Long mr) {
        if (mr != null) {
            List<DocumentationDTO> docs = documentationService.findByPatientMr(mr);
            return docs;
        }
        return documentationService.findAll();
    }

    @PostMapping
    public ResponseEntity<DocumentationDTO> addToRegisterByPatientMr(NewDocumentationDTO body, @RequestParam(required = true) Long mr) {
        return documentationService.createByPatientMr(body, mr);
    }
}
