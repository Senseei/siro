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
import com.internacao.siro.exceptions.InvalidCPFFormatException;
import com.internacao.siro.exceptions.InvalidJsonFormatException;
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
        NewPersonDTO body = new NewPersonDTO("CreatingNewPersonTest", LocalDate.now(), "1");
        assertThrows(InvalidCPFFormatException.class, () -> personService.create(body));
        body.setCpf("11111111111");

        PersonDTO personDTO = personService.create(body).getBody();

        assertNotNull(personDTO);
        assertEquals(body.getName(), personDTO.getName());
        assertEquals(body.getBirthday(), personDTO.getBirthday());
        assertThrows(DuplicateKeyException.class,
                () -> personService.create(new NewPersonDTO("DuplicateTest", LocalDate.now(), "11111111111")));
        assertEquals(body.getCpf(), personDTO.getCpf());

        NewPersonDTO nullFieldsBody = new NewPersonDTO("TestingInvalidInputs", LocalDate.now(), "12345678911");
        nullFieldsBody.setName(null);
        assertThrows(InvalidJsonFormatException.class, () -> personService.create(nullFieldsBody));
        nullFieldsBody.setName("TestingInvalidInputs");
        nullFieldsBody.setBirthday(null);
        assertThrows(InvalidJsonFormatException.class, () -> personService.create(nullFieldsBody));
        nullFieldsBody.setBirthday(LocalDate.now());
        nullFieldsBody.setCpf(null);
        assertThrows(InvalidJsonFormatException.class, () -> personService.create(nullFieldsBody));

        NewPersonDTO emptyFieldsBody = new NewPersonDTO("TestingInvalidInputs", LocalDate.now(), "12345678912");
        emptyFieldsBody.setName("");
        assertThrows(InvalidJsonFormatException.class, () -> personService.create(emptyFieldsBody));
        emptyFieldsBody.setName("    ");
        assertThrows(InvalidJsonFormatException.class, () -> personService.create(emptyFieldsBody));
        emptyFieldsBody.setName("TestingEmptyInputs");
        emptyFieldsBody.setCpf("");
        assertThrows(InvalidJsonFormatException.class, () -> personService.create(emptyFieldsBody));
        emptyFieldsBody.setCpf("           ");
        assertThrows(InvalidJsonFormatException.class, () -> personService.create(emptyFieldsBody));
    }

    @Test
    public void updateTest() {
        UpdatePersonDTO body = new UpdatePersonDTO("UpdatingTestPerson", LocalDate.of(2000, 01, 01), "1");
        assertThrows(InvalidCPFFormatException.class, () -> personService.update(testPerson.getId(), body));
        body.setCpf("11111111111");
        PersonDTO personDTO = personService.update(testPerson.getId(), body).getBody();

        assertNotNull(personDTO);
        assertEquals(body.getName(), personDTO.getName());
        assertEquals(body.getBirthday(), personDTO.getBirthday());
        assertEquals(body.getCpf(), personDTO.getCpf());

        body.setName("");
        assertThrows(InvalidJsonFormatException.class, () -> personService.update(testPerson.getId(), body));
        body.setName("TestingEmptyValues");
        body.setCpf("");
        assertThrows(InvalidJsonFormatException.class, () -> personService.update(testPerson.getId(), body));
    }
}
