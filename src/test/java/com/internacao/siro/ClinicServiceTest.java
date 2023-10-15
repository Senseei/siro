package com.internacao.siro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.clinic.ClinicDTO;
import com.internacao.siro.dto.clinic.NewClinicDTO;
import com.internacao.siro.dto.clinic.UpdateClinicDTO;
import com.internacao.siro.entities.Clinic;
import com.internacao.siro.exceptions.InvalidJsonFormatException;
import com.internacao.siro.repositories.ClinicRepository;
import com.internacao.siro.services.ClinicService;

@SpringBootTest
@Transactional
public class ClinicServiceTest {

    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    ClinicService clinicService;

    Clinic testClinic;

    @BeforeEach
    public void setUp() {
        testClinic = new Clinic("Test Clinic");
        clinicRepository.save(testClinic);
    }

    @AfterEach
    public void tearDown() {
        clinicRepository.delete(testClinic);
    }

    @Test
    public void findAllTest() {
        List<ClinicDTO> result = clinicService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void findById() {
        ClinicDTO result = clinicService.findById(testClinic.getId());

        assertNotNull(result);
        assertEquals(testClinic.getName(), result.getName());
    }

    @Test
    public void findByName() {
        ClinicDTO result = clinicService.findByName("Test Clinic");

        assertNotNull(result);
        assertEquals(result.getName(), testClinic.getName());
    }

    @Test
    public void createTest() {
        NewClinicDTO body = new NewClinicDTO("New Clinic");
        ClinicDTO dto = clinicService.create(body);

        assertNotNull(dto);
        assertEquals(body.getName(), dto.getName());

        assertThrows(DuplicateKeyException.class, () -> clinicService.create(body));

        NewClinicDTO invalidBody = new NewClinicDTO(null);
        assertThrows(InvalidJsonFormatException.class, () -> clinicService.create(invalidBody));
        invalidBody.setName("");
        assertThrows(InvalidJsonFormatException.class, () -> clinicService.create(invalidBody));
        invalidBody.setName("      ");
        assertThrows(InvalidJsonFormatException.class, () -> clinicService.create(invalidBody));
    }

    @Test
    public void updateTest() {
        UpdateClinicDTO body = new UpdateClinicDTO("Updating Clinic");
        ClinicDTO dto = clinicService.update(body, testClinic.getId());

        assertNotNull(dto);
        assertEquals(dto.getName(), body.getName());
        assertEquals(testClinic.getName(), dto.getName());

        UpdateClinicDTO invalidBody = new UpdateClinicDTO("");
        assertThrows(InvalidJsonFormatException.class, () -> clinicService.update(invalidBody, testClinic.getId()));
        invalidBody.setName("    ");
        assertThrows(InvalidJsonFormatException.class, () -> clinicService.update(invalidBody, testClinic.getId()));
    }
}
