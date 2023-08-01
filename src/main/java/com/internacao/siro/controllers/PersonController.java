package com.internacao.siro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.PersonDTO;
import com.internacao.siro.services.PersonService;

@RestController
@RequestMapping(value = {"/people", "/people/"})
public class PersonController {
    
    @Autowired
    PersonService personService;

    @GetMapping
    public List<PersonDTO> findAll() {
        return personService.findAll();
    }

    @GetMapping("/search")
    public PersonDTO findById(@RequestParam Long id) {
        return personService.findById(id);
    }
}
