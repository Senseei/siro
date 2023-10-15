package com.internacao.siro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.employee.EmployeeDTO;
import com.internacao.siro.dto.employee.NewEmployeeDTO;
import com.internacao.siro.dto.employee.UpdateEmployeeDTO;
import com.internacao.siro.entities.Employee;
import com.internacao.siro.exceptions.InvalidJsonFormatException;
import com.internacao.siro.exceptions.NegativeReNumberException;
import com.internacao.siro.repositories.EmployeeRepository;
import com.internacao.siro.services.EmployeeService;

@SpringBootTest
@Transactional
public class EmployeeServiceTest {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;

    Employee testEmployee;

    @BeforeEach
    public void setUp() {
        testEmployee = new Employee("testEmployee", LocalDate.now(), "11111111199", 1010l);
        employeeRepository.save(testEmployee);
    }

    @AfterEach
    public void tearDown() {
        employeeRepository.delete(testEmployee);
    }

    @Test
    public void findAllTestReturnsNotNullList() {
        List<EmployeeDTO> result = employeeService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void findByIdTest() {
        EmployeeDTO retrievedEmployee = employeeService.findById(testEmployee.getId()).getBody();

        assertNotNull(retrievedEmployee);
        assertEmployeeEquals(EmployeeDTO.of(testEmployee), retrievedEmployee);
    }

    @Test
    public void findByReTest() {
        EmployeeDTO retrievedEmployee = employeeService.findByRe(testEmployee.getRe()).getBody();

        assertNotNull(retrievedEmployee);
        assertEmployeeEquals(EmployeeDTO.of(testEmployee), retrievedEmployee);
    }

    @Test
    public void createTest() {
        NewEmployeeDTO body = new NewEmployeeDTO("CreatingNewEmployeeTest", LocalDate.now(), "11111111100", 1001l);
        EmployeeDTO employeeDTO = employeeService.create(body).getBody();

        assertNotNull(employeeDTO);
        assertEquals(body.getName(), employeeDTO.getName());
        assertEquals(body.getBirthday(), employeeDTO.getBirthday());
        assertThrows(DuplicateKeyException.class,
                () -> employeeService
                        .create(new NewEmployeeDTO("DuplicateTest", LocalDate.now(), "00000000000", 1001l)));
        assertEquals(body.getCpf(), employeeDTO.getCpf());
        assertEquals(body.getRe(), employeeDTO.getRe());

        NewEmployeeDTO nullFieldsBody = new NewEmployeeDTO("TestingInvalidInputs", LocalDate.now(), "11111111111", 1l);
        nullFieldsBody.setName(null);
        assertThrows(InvalidJsonFormatException.class, () -> employeeService.create(nullFieldsBody));
        nullFieldsBody.setName("TestingInvalidInputs");
        nullFieldsBody.setBirthday(null);
        assertThrows(InvalidJsonFormatException.class, () -> employeeService.create(nullFieldsBody));
        nullFieldsBody.setBirthday(LocalDate.now());
        nullFieldsBody.setCpf(null);
        assertThrows(InvalidJsonFormatException.class, () -> employeeService.create(nullFieldsBody));
        nullFieldsBody.setCpf("11111111111");
        nullFieldsBody.setRe(-1l);
        assertThrows(NegativeReNumberException.class, () -> employeeService.create(nullFieldsBody));

        NewEmployeeDTO emptyFieldsBody = new NewEmployeeDTO("TestingInvalidInputs", LocalDate.now(), "11111111112", 2l);
        emptyFieldsBody.setName("");
        assertThrows(InvalidJsonFormatException.class, () -> employeeService.create(emptyFieldsBody));
        emptyFieldsBody.setName("     ");
        assertThrows(InvalidJsonFormatException.class, () -> employeeService.create(emptyFieldsBody));
        emptyFieldsBody.setName("TestingEmptyValues");
        emptyFieldsBody.setCpf("");
        assertThrows(InvalidJsonFormatException.class, () -> employeeService.create(emptyFieldsBody));
        emptyFieldsBody.setCpf("           ");
        assertThrows(InvalidJsonFormatException.class, () -> employeeService.create(emptyFieldsBody));
    }

    @Test
    public void updateTest() {
        UpdateEmployeeDTO body = new UpdateEmployeeDTO("UpdatingtestEmployee", LocalDate.now(), "11111111100", 1l);
        EmployeeDTO EmployeeDTO = employeeService.update(testEmployee.getId(), body).getBody();

        assertNotNull(EmployeeDTO);
        assertEquals(body.getName(), EmployeeDTO.getName());
        assertEquals(body.getBirthday(), EmployeeDTO.getBirthday());
        assertEquals(body.getCpf(), EmployeeDTO.getCpf());

        UpdateEmployeeDTO invalidBody = new UpdateEmployeeDTO("TestingInvalidInputs", LocalDate.now(), "11111111111",
                1l);

        invalidBody.setName("");
        assertThrows(InvalidJsonFormatException.class, () -> employeeService.update(testEmployee.getId(), invalidBody));
        invalidBody.setName("        ");
        assertThrows(InvalidJsonFormatException.class, () -> employeeService.update(testEmployee.getId(), invalidBody));
        invalidBody.setName("TestingInvalidInputs");
        invalidBody.setCpf("");
        assertThrows(InvalidJsonFormatException.class, () -> employeeService.update(testEmployee.getId(), invalidBody));
        invalidBody.setCpf("           ");
        assertThrows(InvalidJsonFormatException.class, () -> employeeService.update(testEmployee.getId(), invalidBody));
        invalidBody.setCpf("11111111111");
        invalidBody.setRe(-1l);
        assertThrows(NegativeReNumberException.class,
                () -> employeeService.update(testEmployee.getId(), invalidBody));
    }

    private void assertEmployeeEquals(EmployeeDTO Employee1, EmployeeDTO Employee2) {
        assertEquals(Employee1.getName(), Employee2.getName());
        assertEquals(Employee1.getBirthday(), Employee2.getBirthday());
        assertEquals(Employee1.getCpf(), Employee2.getCpf());
        assertEquals(Employee1.getRe(), Employee2.getRe());
    }
}
