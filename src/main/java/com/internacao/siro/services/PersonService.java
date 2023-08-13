package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.doctor.DoctorDTO;
import com.internacao.siro.dto.employee.EmployeeDTO;
import com.internacao.siro.dto.patient.PatientDTO;
import com.internacao.siro.dto.person.NewPersonDTO;
import com.internacao.siro.dto.person.PersonDTO;
import com.internacao.siro.dto.person.UpdatePersonDTO;
import com.internacao.siro.entities.Doctor;
import com.internacao.siro.entities.Employee;
import com.internacao.siro.entities.Patient;
import com.internacao.siro.entities.Person;
import com.internacao.siro.repositories.PersonRepository;

@Service
public class PersonService {
    
    @Autowired
    PersonRepository personRepository;

    @Transactional(readOnly = true)
    public List<PersonDTO> findAll() {
        List<Person> result = personRepository.findAll();
        List<PersonDTO> dto = result.stream().map(x -> new PersonDTO(x)).toList();
        return dto;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<PersonDTO> findById(Long id) {
        Person person = personRepository.findById(id).orElse(null);

        if (person == null)
            return ResponseEntity.notFound().build();

        if (person instanceof Doctor) {
            DoctorDTO dto = new DoctorDTO((Doctor) person);
            return ResponseEntity.ok((PersonDTO) dto);
        }
        else if (person instanceof Employee) {
            EmployeeDTO dto = new EmployeeDTO((Employee) person);
            return ResponseEntity.ok((PersonDTO) dto);
        }
        else if (person instanceof Patient) {
            PatientDTO dto = new PatientDTO((Patient) person);
            return ResponseEntity.ok((PersonDTO) dto);
        }
        return ResponseEntity.ok(new PersonDTO(person));
    }

    @Transactional
    public ResponseEntity<PersonDTO> create(NewPersonDTO body) {
        if (body.getCpf() != null) {
            Person exists = personRepository.findByCpf(body.getCpf());

            if (exists != null)
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Person newPerson = new Person(body);
        personRepository.save(newPerson);
        return ResponseEntity.ok(new PersonDTO(newPerson));
    }

    @Transactional
    public ResponseEntity<PersonDTO> update(Long id, UpdatePersonDTO body) {
        Person person = personRepository.findById(id).orElse(null);
        
        if (person == null)
            return ResponseEntity.notFound().build();

        person.update(body);
        personRepository.save(person);
        return ResponseEntity.ok(new PersonDTO(person));
    }
}
