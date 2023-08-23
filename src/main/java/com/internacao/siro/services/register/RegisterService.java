package com.internacao.siro.services.register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.register.NewRegisterDTO;
import com.internacao.siro.dto.register.RegisterDTO;
import com.internacao.siro.dto.register.UpdateRegisterDTO;
import com.internacao.siro.entities.Clinic;
import com.internacao.siro.entities.Doctor;
import com.internacao.siro.entities.Patient;
import com.internacao.siro.entities.Register;
import com.internacao.siro.repositories.PatientRepository;
import com.internacao.siro.repositories.RegisterRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class RegisterService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    RegisterUtil registerUtil;
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    PatientRepository patientRepository;


    @Transactional(readOnly = true)
    public List<RegisterDTO> findAll() {
        List<Register> result = registerRepository.findAll();

        return result.stream().map(x -> registerUtil.createRegisterDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<RegisterDTO> findById(Long id) {
        Register register = registerRepository.findById(id).orElse(null);
        if (register == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(registerUtil.createRegisterDTO(register));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<RegisterDTO> findByPatientId(Long patientId) {
        Register register = registerUtil.checkIfRegisterExistsByPatientId(patientId);
        if (register == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(registerUtil.createRegisterDTO(register));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<RegisterDTO> findByPatientMr(Long mr) {
        Register register = registerUtil.checkIfRegisterExistsByMr(mr);
        if (register == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(registerUtil.createRegisterDTO(register));
    }

    @Transactional
    public ResponseEntity<RegisterDTO> create(NewRegisterDTO body) {

        registerUtil.validateJson(body);

        if (registerRepository.existsByPatientId(body.getPatientId()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Patient patient = entityManager.getReference(Patient.class, body.getPatientId());
        Doctor doctor = entityManager.getReference(Doctor.class, body.getDoctorId());
        Clinic clinic = entityManager.getReference(Clinic.class, body.getClinicId());

        Register newRegister = new Register(patient, body.getDateOfDeath(), doctor, clinic);
        registerRepository.save(newRegister);
        return ResponseEntity.ok(registerUtil.createRegisterDTO(newRegister));
    }

    @Transactional
    public ResponseEntity<RegisterDTO> update(Long id, UpdateRegisterDTO body) {
        
        Register register = registerRepository.findById(id).orElse(null);
        if (register == null)
            return ResponseEntity.notFound().build();
        
        registerUtil.updateDTOToEntity(register, body);
        registerRepository.save(register);
        return ResponseEntity.ok(registerUtil.createRegisterDTO(register));
    }
}
