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

import com.internacao.siro.dto.patient.NewPatientDTO;
import com.internacao.siro.dto.patient.PatientDTO;
import com.internacao.siro.dto.patient.UpdatePatientDTO;
import com.internacao.siro.dto.relative.RelativeDTO;
import com.internacao.siro.entities.Patient;
import com.internacao.siro.entities.Person;
import com.internacao.siro.entities.Relative;
import com.internacao.siro.exceptions.InvalidJsonFormatException;
import com.internacao.siro.exceptions.NegativeMedicalRecordException;
import com.internacao.siro.repositories.PatientRepository;
import com.internacao.siro.repositories.PersonRepository;
import com.internacao.siro.repositories.RelativeRepository;
import com.internacao.siro.services.PatientService;

@SpringBootTest
@Transactional
public class PatientServiceTest {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientService patientService;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    RelativeRepository relativeRepository;

    Patient testPatient;

    @BeforeEach
    public void setUp() {
        testPatient = new Patient("testPatient", LocalDate.now(), "11111111199", 1234l);
        patientRepository.save(testPatient);
    }

    @AfterEach
    public void tearDown() {
        patientRepository.delete(testPatient);
    }

    @Test
    public void findAllTestReturnsNotNullList() {
        List<PatientDTO> result = patientService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void findByIdTest() {
        PatientDTO retrievedPatient = patientService.findById(testPatient.getId()).getBody();

        assertNotNull(retrievedPatient);
        assertPatientEquals(PatientDTO.of(testPatient), retrievedPatient);
    }

    @Test
    public void findByMrTest() {
        PatientDTO retrievedPatient = patientService.findByMr(testPatient.getMr()).getBody();

        assertNotNull(retrievedPatient);
        assertPatientEquals(PatientDTO.of(testPatient), retrievedPatient);
    }

    @Test
    public void createTest() {
        NewPatientDTO body = new NewPatientDTO("CreatingNewPatientTest", LocalDate.now(), "11111111100", 1001l);
        PatientDTO patientDTO = patientService.create(body).getBody();

        assertNotNull(patientDTO);
        assertEquals(body.getName(), patientDTO.getName());
        assertEquals(body.getBirthday(), patientDTO.getBirthday());
        assertThrows(DuplicateKeyException.class,
                () -> patientService.create(new NewPatientDTO("DuplicateTest", LocalDate.now(), "00000000000", 1001l)));
        assertEquals(body.getCpf(), patientDTO.getCpf());
        assertEquals(body.getMr(), patientDTO.getMr());

        NewPatientDTO nullFieldsBody = new NewPatientDTO("TestingInvalidInputs", LocalDate.now(), "11111111111", 1l);
        nullFieldsBody.setName(null);
        assertThrows(InvalidJsonFormatException.class, () -> patientService.create(nullFieldsBody));
        nullFieldsBody.setName("TestingInvalidInputs");
        nullFieldsBody.setBirthday(null);
        assertThrows(InvalidJsonFormatException.class, () -> patientService.create(nullFieldsBody));
        nullFieldsBody.setBirthday(LocalDate.now());
        nullFieldsBody.setCpf(null);
        assertThrows(InvalidJsonFormatException.class, () -> patientService.create(nullFieldsBody));
        nullFieldsBody.setCpf("11111111111");
        nullFieldsBody.setMr(-1l);
        assertThrows(NegativeMedicalRecordException.class, () -> patientService.create(nullFieldsBody));

        NewPatientDTO emptyFieldsBody = new NewPatientDTO("TestingInvalidInputs", LocalDate.now(), "11111111112", 2l);
        emptyFieldsBody.setName("");
        assertThrows(InvalidJsonFormatException.class, () -> patientService.create(emptyFieldsBody));
        emptyFieldsBody.setName("     ");
        assertThrows(InvalidJsonFormatException.class, () -> patientService.create(emptyFieldsBody));
        emptyFieldsBody.setName("TestingEmptyValues");
        emptyFieldsBody.setCpf("");
        assertThrows(InvalidJsonFormatException.class, () -> patientService.create(emptyFieldsBody));
        emptyFieldsBody.setCpf("           ");
        assertThrows(InvalidJsonFormatException.class, () -> patientService.create(emptyFieldsBody));
    }

    @Test
    public void updateTest() {
        UpdatePatientDTO body = new UpdatePatientDTO("UpdatingTestPatient", LocalDate.now(), "11111111100", 1l);
        PatientDTO patientDTO = patientService.update(testPatient.getId(), body).getBody();

        assertNotNull(patientDTO);
        assertEquals(body.getName(), patientDTO.getName());
        assertEquals(body.getBirthday(), patientDTO.getBirthday());
        assertEquals(body.getCpf(), patientDTO.getCpf());

        UpdatePatientDTO invalidBody = new UpdatePatientDTO("TestingInvalidInputs", LocalDate.now(), "11111111111", 1l);

        invalidBody.setName("");
        assertThrows(InvalidJsonFormatException.class, () -> patientService.update(testPatient.getId(), invalidBody));
        invalidBody.setName("        ");
        assertThrows(InvalidJsonFormatException.class, () -> patientService.update(testPatient.getId(), invalidBody));
        invalidBody.setName("TestingInvalidInputs");
        invalidBody.setCpf("");
        assertThrows(InvalidJsonFormatException.class, () -> patientService.update(testPatient.getId(), invalidBody));
        invalidBody.setCpf("           ");
        assertThrows(InvalidJsonFormatException.class, () -> patientService.update(testPatient.getId(), invalidBody));
        invalidBody.setCpf("11111111111");
        invalidBody.setMr(-1l);
        assertThrows(NegativeMedicalRecordException.class,
                () -> patientService.update(testPatient.getId(), invalidBody));
    }

    @Test
    public void getRelativesTest() {
        Person relative = new Person("Gilberto");
        personRepository.save(relative);
        Relative relativeEntity = new Relative(relative, testPatient, "Brother");
        relativeRepository.save(relativeEntity);

        List<RelativeDTO> relatives = patientService.relatives(testPatient.getId());
        assertNotNull(relatives);
        assertEquals(relativeEntity.getRelative().getName(), relatives.get(0).getName());
        assertEquals(relativeEntity.getRelationship(), relatives.get(0).getRelationship());

        personRepository.delete(relative);
        relativeRepository.delete(relativeEntity);
    }

    private void assertPatientEquals(PatientDTO patient1, PatientDTO patient2) {
        assertEquals(patient1.getName(), patient2.getName());
        assertEquals(patient1.getBirthday(), patient2.getBirthday());
        assertEquals(patient1.getCpf(), patient2.getCpf());
        assertEquals(patient1.getMr(), patient2.getMr());
    }
}
