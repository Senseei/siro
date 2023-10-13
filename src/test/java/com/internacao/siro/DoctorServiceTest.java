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

import com.internacao.siro.dto.doctor.DoctorDTO;
import com.internacao.siro.dto.doctor.NewDoctorDTO;
import com.internacao.siro.dto.doctor.UpdateDoctorDTO;
import com.internacao.siro.entities.Doctor;
import com.internacao.siro.exceptions.InvalidJsonFormatException;
import com.internacao.siro.exceptions.NegativeCrmNumberException;
import com.internacao.siro.repositories.DoctorRepository;
import com.internacao.siro.services.DoctorService;

@SpringBootTest
@Transactional
public class DoctorServiceTest {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DoctorService doctorService;

    Doctor testDoctor;

    @BeforeEach
    public void setUp() {
        testDoctor = new Doctor("testDoctor", LocalDate.now(), "11111111199", 1010l);
        doctorRepository.save(testDoctor);
    }

    @AfterEach
    public void tearDown() {
        doctorRepository.delete(testDoctor);
    }

    @Test
    public void findAllTestReturnsNotNullList() {
        List<DoctorDTO> result = doctorService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void findByIdTest() {
        DoctorDTO retrievedDoctor = doctorService.findById(testDoctor.getId()).getBody();

        assertNotNull(retrievedDoctor);
        assertDoctorEquals(DoctorDTO.of(testDoctor), retrievedDoctor);
    }

    @Test
    public void findByCrmTest() {
        DoctorDTO retrievedDoctor = doctorService.findByCrm(testDoctor.getCrm()).getBody();

        assertNotNull(retrievedDoctor);
        assertDoctorEquals(DoctorDTO.of(testDoctor), retrievedDoctor);
    }

    @Test
    public void createTest() {
        NewDoctorDTO body = new NewDoctorDTO("CreatingNewDoctorTest", LocalDate.now(), "11111111100", 1001l);
        DoctorDTO doctorDTO = doctorService.create(body).getBody();

        assertNotNull(doctorDTO);
        assertEquals(body.getName(), doctorDTO.getName());
        assertEquals(body.getBirthday(), doctorDTO.getBirthday());
        assertThrows(DuplicateKeyException.class,
                () -> doctorService.create(new NewDoctorDTO("DuplicateTest", LocalDate.now(), "00000000000", 1001l)));
        assertEquals(body.getCpf(), doctorDTO.getCpf());
        assertEquals(body.getCrm(), doctorDTO.getCrm());

        NewDoctorDTO nullFieldsBody = new NewDoctorDTO("TestingInvalidInputs", LocalDate.now(), "11111111111", 1l);
        nullFieldsBody.setName(null);
        assertThrows(InvalidJsonFormatException.class, () -> doctorService.create(nullFieldsBody));
        nullFieldsBody.setName("TestingInvalidInputs");
        nullFieldsBody.setBirthday(null);
        assertThrows(InvalidJsonFormatException.class, () -> doctorService.create(nullFieldsBody));
        nullFieldsBody.setBirthday(LocalDate.now());
        nullFieldsBody.setCpf(null);
        assertThrows(InvalidJsonFormatException.class, () -> doctorService.create(nullFieldsBody));
        nullFieldsBody.setCpf("11111111111");
        nullFieldsBody.setCrm(-1l);
        assertThrows(NegativeCrmNumberException.class, () -> doctorService.create(nullFieldsBody));

        NewDoctorDTO emptyFieldsBody = new NewDoctorDTO("TestingInvalidInputs", LocalDate.now(), "11111111112", 2l);
        emptyFieldsBody.setName("");
        assertThrows(InvalidJsonFormatException.class, () -> doctorService.create(emptyFieldsBody));
        emptyFieldsBody.setName("     ");
        assertThrows(InvalidJsonFormatException.class, () -> doctorService.create(emptyFieldsBody));
        emptyFieldsBody.setName("TestingEmptyValues");
        emptyFieldsBody.setCpf("");
        assertThrows(InvalidJsonFormatException.class, () -> doctorService.create(emptyFieldsBody));
        emptyFieldsBody.setCpf("           ");
        assertThrows(InvalidJsonFormatException.class, () -> doctorService.create(emptyFieldsBody));
    }

    @Test
    public void updateTest() {
        UpdateDoctorDTO body = new UpdateDoctorDTO("UpdatingTestDoctor", LocalDate.now(), "11111111100", 1l);
        DoctorDTO doctorDTO = doctorService.update(testDoctor.getId(), body).getBody();

        assertNotNull(doctorDTO);
        assertEquals(body.getName(), doctorDTO.getName());
        assertEquals(body.getBirthday(), doctorDTO.getBirthday());
        assertEquals(body.getCpf(), doctorDTO.getCpf());

        UpdateDoctorDTO invalidBody = new UpdateDoctorDTO("TestingInvalidInputs", LocalDate.now(), "11111111111", 1l);

        invalidBody.setName("");
        assertThrows(InvalidJsonFormatException.class, () -> doctorService.update(testDoctor.getId(), invalidBody));
        invalidBody.setName("        ");
        assertThrows(InvalidJsonFormatException.class, () -> doctorService.update(testDoctor.getId(), invalidBody));
        invalidBody.setName("TestingInvalidInputs");
        invalidBody.setCpf("");
        assertThrows(InvalidJsonFormatException.class, () -> doctorService.update(testDoctor.getId(), invalidBody));
        invalidBody.setCpf("           ");
        assertThrows(InvalidJsonFormatException.class, () -> doctorService.update(testDoctor.getId(), invalidBody));
        invalidBody.setCpf("11111111111");
        invalidBody.setCrm(-1l);
        assertThrows(NegativeCrmNumberException.class,
                () -> doctorService.update(testDoctor.getId(), invalidBody));
    }

    private void assertDoctorEquals(DoctorDTO doctor1, DoctorDTO doctor2) {
        assertEquals(doctor1.getName(), doctor2.getName());
        assertEquals(doctor1.getBirthday(), doctor2.getBirthday());
        assertEquals(doctor1.getCpf(), doctor2.getCpf());
        assertEquals(doctor1.getCrm(), doctor2.getCrm());
    }
}
