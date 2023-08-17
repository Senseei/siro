package com.internacao.siro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.register.NewRegisterDTO;
import com.internacao.siro.dto.register.RegisterDTO;
import com.internacao.siro.entities.Clinic;
import com.internacao.siro.entities.Doctor;
import com.internacao.siro.entities.Patient;
import com.internacao.siro.entities.Register;
import com.internacao.siro.projections.RelativeProjection;
import com.internacao.siro.repositories.ClinicRepository;
import com.internacao.siro.repositories.DoctorRepository;
import com.internacao.siro.repositories.PatientRepository;
import com.internacao.siro.repositories.RegisterRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

@Service
public class RegisterService {

    @PersistenceContext
    EntityManager entityManager;
    
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    ClinicRepository clinicRepository;


    @Transactional(readOnly = true)
    public List<RegisterDTO> findAll() {
        List<Register> result = registerRepository.findAll();
        List<RegisterDTO> dto = result.stream().map(x -> createRegisterDTO(x)).toList();
        return dto;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<RegisterDTO> findById(Long id) {
        Register result = registerRepository.findById(id).orElse(null);
        if (result == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(createRegisterDTO(result));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<RegisterDTO> findByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        RegisterDTO register = createRegisterDTO(patient);
        if (register == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(register);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<RegisterDTO> findByPatientMr(Long mr) {
        Patient patient = patientRepository.findByMr(mr);
        RegisterDTO register = createRegisterDTO(patient);
        if (register == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(register);
    }

    @Transactional
    public ResponseEntity<RegisterDTO> create(NewRegisterDTO body) {

        if (!patientRepository.existsById(body.getPatientId()))
            throw new EntityNotFoundException("This patient does not exist");
        if (!doctorRepository.existsById(body.getDoctorId()))
            throw new EntityNotFoundException("This doctor does not exist");
        if (!clinicRepository.existsById(body.getClinicId()))
            throw new EntityNotFoundException("This clinic does not exist");

        Patient patient = entityManager.getReference(Patient.class, body.getPatientId());
        Doctor doctor = entityManager.getReference(Doctor.class, body.getDoctorId());
        Clinic clinic = entityManager.getReference(Clinic.class, body.getClinicId());

        if (registerRepository.existsByPatientId(body.getPatientId()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Register newRegister = new Register(patient, body.getDateOfDeath(), doctor, clinic);
        registerRepository.save(newRegister);
        return ResponseEntity.ok(createRegisterDTO(newRegister));
    }


    private RegisterDTO createRegisterDTO(Register register) {
        if (register.getRelative() != null) {
                RelativeProjection relativeProjection = patientRepository.findRelativeById(
                    register.getRelative().getId(), register.getPatient().getId());
                return new RegisterDTO(register, relativeProjection);
            }
            return new RegisterDTO(register);
    }

    private RegisterDTO createRegisterDTO(Patient patient) {
        Optional<Register> registerOpt = Optional.ofNullable(registerRepository.findByPatient(patient));

        return registerOpt.map(register -> {
            RelativeProjection relativeProjection = null;
            if (register.getRelative() != null) {
                relativeProjection = patientRepository.findRelativeById(register.getRelative().getId(), patient.getId());
            }
            return new RegisterDTO(register, relativeProjection);
        }).orElse(null);
    }
}
