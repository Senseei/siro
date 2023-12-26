package com.internacao.siro;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;

import com.internacao.siro.dto.person.NewPersonDTO;
import com.internacao.siro.dto.person.PersonDTO;
import com.internacao.siro.entities.Person;
import com.internacao.siro.exceptions.InvalidCPFFormatException;
import com.internacao.siro.repositories.PersonRepository;
import com.internacao.siro.services.PersonService;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Person Service Test")
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @MockBean
    PersonRepository personRepository;

    @Nested
    @DisplayName("Find All method tests")
    class FindAllTest {

        Person person1 = new Person("TestPerson", LocalDate.of(2000, 1, 1), "12345678910");
        Person person2 = new Person("TestPerson2", LocalDate.of(2000, 1, 1), "12345678911");

        @BeforeEach
        void setUp() {
            when(personRepository.findAll()).thenReturn(List.of(person1, person2));
        }

        @Test
        @DisplayName("Check if method returns a NonNull list")
        void returnsNotNullList() {
            List<PersonDTO> result = personService.findAll();
            assertNotNull(result);
        }

        @Test
        @DisplayName("Returns a list of people")
        void returnsValidList() {
            List<PersonDTO> dtos = List.of(
                new PersonDTO(person1),
                new PersonDTO(person2)
            );

            List<PersonDTO> result = personService.findAll();
            for (int i = 0; i < result.size(); i++)
                assertThat(result.get(i).getId()).isEqualTo(dtos.get(i).getId());
        }
    }

    @Nested
    @DisplayName("Find By Id tests")
    class FindByIdTest {

        Person testPerson = new Person("TestPerson", LocalDate.of(2000, 01, 01), "12345678911");
        PersonDTO retrievedPerson;

        @BeforeEach
        void setUp() {
            when(testPerson.getId()).thenReturn(1L);
            when(personRepository.findById(testPerson.getId())).thenReturn(Optional.of(testPerson));
            retrievedPerson = personService.findById(testPerson.getId());
        }

        @Test
        void returnsNotNullValue() {
            assertNotNull(retrievedPerson);
        }

        @Test
        void returnsCorrectObjectPerson() {
            assertThat(retrievedPerson.getId()).isEqualTo(testPerson.getId());
        }

        @Test
        void checkIfInfoMatch() {
            assertThat(retrievedPerson.getName()).isEqualTo(testPerson.getName());
            assertThat(retrievedPerson.getBirthday()).isEqualTo(testPerson.getBirthday());
            assertThat(retrievedPerson.getCpf()).isEqualTo(testPerson.getCpf());
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
