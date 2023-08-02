package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.DoctorDTO;
import com.internacao.siro.dto.EmployeeDTO;
import com.internacao.siro.dto.PatientDTO;
import com.internacao.siro.dto.PersonDTO;
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
    public PersonDTO findById(Long id) {
        Person person = personRepository.findById(id).orElse(null);

        if (person instanceof Doctor) {
            DoctorDTO dto = new DoctorDTO((Doctor) person);
            return (PersonDTO) dto;
        }
        else if (person instanceof Employee) {
            EmployeeDTO dto = new EmployeeDTO((Employee) person);
            return (PersonDTO) dto;
        }
        else if (person instanceof Patient) {
            PatientDTO dto = new PatientDTO((Patient) person);
            return (PersonDTO) dto;
        }
        return new PersonDTO(person);
    }
}
