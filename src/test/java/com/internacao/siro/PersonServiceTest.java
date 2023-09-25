package com.internacao.siro;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.person.NewPersonDTO;
import com.internacao.siro.dto.person.PersonDTO;
import com.internacao.siro.dto.person.UpdatePersonDTO;
import com.internacao.siro.entities.Person;
import com.internacao.siro.exception.InvalidJsonFormatException;
import com.internacao.siro.repositories.PersonRepository;
import com.internacao.siro.services.PersonService;

@SpringBootTest
@Transactional
public class PersonServiceTest {
    
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonService personService;

    Person testPerson;

    @BeforeEach
    public void setUp() {
        testPerson = new Person("TestPerson", LocalDate.now());
        personRepository.save(testPerson);
    }

    @AfterEach
    public void tearDown() {
        personRepository.delete(testPerson);
    }


    @Test
    public void findAllTestReturnsNotNullList() {
        List<PersonDTO> result = personService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void findByIdTest() {
        PersonDTO retrievedPerson = personService.findById(testPerson.getId()).getBody();

        assertNotNull(retrievedPerson);
        assertEquals("TestPerson", retrievedPerson.getName());
        assertEquals(LocalDate.now(), retrievedPerson.getBirthday());
    }

    @Test
    public void createTest() {
        NewPersonDTO body = new NewPersonDTO("CreatingNewPersonTest", LocalDate.now(), "123");
        PersonDTO personDTO = personService.create(body).getBody();

        assertNotNull(personDTO);
        assertEquals("CreatingNewPersonTest", personDTO.getName());
        assertEquals(LocalDate.now(), personDTO.getBirthday());
        assertThrows(DuplicateKeyException.class, () -> personService.create(new NewPersonDTO("DuplicateTest", LocalDate.now(), "123")));
        assertEquals("123", personDTO.getCpf());
        
        assertThrows(InvalidJsonFormatException.class, () -> personService.create(new NewPersonDTO(null, LocalDate.now(), "000")));
        assertThrows(InvalidJsonFormatException.class, () -> personService.create(new NewPersonDTO("TestingInvalidInputs", null, "000")));
        assertThrows(InvalidJsonFormatException.class, () -> personService.create(new NewPersonDTO("TestingInvalidInputs", LocalDate.now(), null)));
    }

    @Test
    public void updateTest() {
        UpdatePersonDTO body = new UpdatePersonDTO("UpdatingTestPerson", LocalDate.of(2000, 01, 01), "000");
        PersonDTO personDTO = personService.update(testPerson.getId(), body).getBody();

        assertNotNull(personDTO);
        assertEquals("UpdatingTestPerson", personDTO.getName());
        assertEquals(LocalDate.of(2000, 01, 01), personDTO.getBirthday());
        assertEquals("000", personDTO.getCpf());

        assertThrows(InvalidJsonFormatException.class, () -> personService.update(testPerson.getId(), new UpdatePersonDTO("", null, null)));
        assertThrows(InvalidJsonFormatException.class, () -> personService.update(testPerson.getId(), new UpdatePersonDTO(null, null, "")));
    }
}
