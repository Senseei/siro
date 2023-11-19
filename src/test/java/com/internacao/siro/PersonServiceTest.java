package com.internacao.siro;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.person.NewPersonDTO;
import com.internacao.siro.dto.person.PersonDTO;
import com.internacao.siro.entities.Person;
import com.internacao.siro.exceptions.InvalidCPFFormatException;
import com.internacao.siro.repositories.PersonRepository;
import com.internacao.siro.services.PersonService;

@SpringBootTest
@Transactional
public class PersonServiceTest {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonService personService;

    @Nested
    @DisplayName("Find All method tests")
    class FindAllTest {

        @Test
        @DisplayName("Check if method returns a NonNull list")
        void findAllTestReturnsNotNullList() {
            List<PersonDTO> result = personService.findAll();
            assertNotNull(result, "FindAll method must return a not null list");
        }
    }

    @Nested
    @DisplayName("Find By Id tests")
    class FindByIdTest {

        Person testPerson;
        PersonDTO retrievedPerson;

        @BeforeEach
        void setUp() {
            testPerson = new Person("TestPerson", LocalDate.now());
            personRepository.save(testPerson);
            retrievedPerson = personService.findById(testPerson.getId());
        }

        @Test
        void findByIdReturnsNotNullValue() {
            assertNotNull(retrievedPerson);
        }

        @Test
        void findByIdCheckIfInfoMatch() {
            assertEquals(testPerson.getName(), retrievedPerson.getName());
            assertEquals(LocalDate.now(), retrievedPerson.getBirthday());
        }
    }

    @Nested
    @DisplayName("Create method tests")
    class CreateTest {

        NewPersonDTO validBody;
        NewPersonDTO invalidBody;
        PersonDTO result;

        @BeforeEach
        void setUp() {
            validBody = new NewPersonDTO("Valid Body Test", LocalDate.now(), "11111111111");
            invalidBody = new NewPersonDTO("Invalid Body Test", LocalDate.now(), "1");
        }

        @Test
        void createWithInvalidInputThrowsInvalidCPFFormatException() {
            assertThrows(InvalidCPFFormatException.class, () -> personService.create(invalidBody));
        }

        @Test
        void createWithValidInputReturnsNotNullValue() {
            result = personService.create(validBody);
            assertNotNull(result);
        }

        @Test
        void createWithValidInputCheckIfInfoMatch() {
            result = personService.create(validBody);
            assertEquals(validBody.getName(), result.getName());
            assertEquals(validBody.getBirthday(), result.getBirthday());
        }

        @Test
        void createWithDuplicateCPFThrowsDuplicateKeyException() {
            personService.create(validBody);
            assertThrows(DuplicateKeyException.class, () -> personService.create(validBody));
        }
    }
}
