package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.person.NewPersonDTO;
import com.internacao.siro.dto.person.PersonDTO;
import com.internacao.siro.dto.person.UpdatePersonDTO;
import com.internacao.siro.entities.Person;
import com.internacao.siro.repositories.PersonRepository;

@Service
public class PersonService {
    
    @Autowired
    PersonRepository personRepository;

    @Transactional(readOnly = true)
    public List<PersonDTO> findAll() {
        List<Person> result = personRepository.findAll();
        return result.stream().map(x -> PersonDTO.of(x)).toList();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<PersonDTO> findById(Long id) {
        Person person = personRepository.findById(id).orElse(null);

        if (person == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Person.toDTO(person));
    }

    @Transactional
    public ResponseEntity<PersonDTO> create(NewPersonDTO body) {
        if (body.getCpf() != null) {
            if (personRepository.existsByCpf(body.getCpf()))
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Person newPerson = new Person(body);
        personRepository.save(newPerson);
        return ResponseEntity.ok(PersonDTO.of(newPerson));
    }

    @Transactional
    public ResponseEntity<PersonDTO> update(Long id, UpdatePersonDTO body) {
        Person person = personRepository.findById(id).orElse(null);
        
        if (person == null)
            return ResponseEntity.notFound().build();

        person.update(body);
        personRepository.save(person);
        return ResponseEntity.ok(PersonDTO.of(person));
    }
}
