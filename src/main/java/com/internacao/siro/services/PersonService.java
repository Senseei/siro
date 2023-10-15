package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.person.NewPersonDTO;
import com.internacao.siro.dto.person.PersonDTO;
import com.internacao.siro.dto.person.UpdatePersonDTO;
import com.internacao.siro.entities.Person;
import com.internacao.siro.repositories.PersonRepository;
import com.internacao.siro.validators.json.PersonJson;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonJson personJson;

    @Transactional(readOnly = true)
    public List<PersonDTO> findAll() {
        List<Person> result = personRepository.findAll();
        return result.stream().map(x -> PersonDTO.of(x)).toList();
    }

    @Transactional(readOnly = true)
    public PersonDTO findById(Long id) {
        Person person = personRepository.findById(id).orElse(null);

        if (person == null)
            throw new EntityNotFoundException();
        return Person.toDTO(person);
    }

    @Transactional
    public PersonDTO create(NewPersonDTO body) {
        if (body.getCpf() != null && personRepository.existsByCpf(body.getCpf()))
            throw new DuplicateKeyException("There is already a person with the given CPF");

        personJson.validate(body);

        Person newPerson = new Person(body);
        personRepository.save(newPerson);
        return PersonDTO.of(newPerson);
    }

    @Transactional
    public PersonDTO update(Long id, UpdatePersonDTO body) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null)
            throw new EntityNotFoundException();

        personJson.validate(body);

        person.update(body);
        personRepository.save(person);
        return PersonDTO.of(person);
    }
}
