package com.internacao.siro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internacao.siro.dto.person.NewPersonDTO;
import com.internacao.siro.dto.person.PersonDTO;
import com.internacao.siro.dto.person.UpdatePersonDTO;
import com.internacao.siro.services.PersonService;

@RestController
@RequestMapping(value = "/people")
public class PersonController {
    
    @Autowired
    PersonService personService;

    @GetMapping
    public List<PersonDTO> findAll() {
        return personService.findAll();
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createNewPerson(@RequestBody NewPersonDTO body) {
        return personService.createNewPerson(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody UpdatePersonDTO body) {
        return personService.updatePerson(id, body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        return personService.deletePerson(id);
    }
}
