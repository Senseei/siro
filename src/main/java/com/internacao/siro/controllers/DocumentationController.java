package com.internacao.siro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.documentation.CancelDocumentationDTO;
import com.internacao.siro.dto.documentation.DocumentationDTO;
import com.internacao.siro.dto.documentation.NewDocumentationDTO;
import com.internacao.siro.dto.documentation.UpdateDocumentationDTO;
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
        return documentationService.appendToRegisterByPatientMr(body, mr);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentationDTO> update(@RequestBody UpdateDocumentationDTO body, @PathVariable Long id) {
        return documentationService.update(body, id);
    }

    @PatchMapping("{id}")
    public ResponseEntity<String> cancel(@RequestParam(required = true) String action, @RequestBody CancelDocumentationDTO body, @PathVariable Long id) {
        if ("cancel".equals(action))
            return documentationService.cancel(body, id);
        return ResponseEntity.badRequest().body("Invalid action");
    }
}
