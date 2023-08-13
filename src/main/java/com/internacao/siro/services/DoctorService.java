package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.doctor.DoctorDTO;
import com.internacao.siro.dto.doctor.NewDoctorDTO;
import com.internacao.siro.dto.doctor.UpdateDoctorDTO;
import com.internacao.siro.entities.Doctor;
import com.internacao.siro.repositories.DoctorRepository;

@Service
public class DoctorService {
    
    @Autowired
    DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public List<DoctorDTO> findAll() {
        List<Doctor> result = doctorRepository.findAll();
        List<DoctorDTO> dto = result.stream().map(x -> new DoctorDTO(x)).toList();
        return dto;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<DoctorDTO> findById(Long id) {
        Doctor result = doctorRepository.findById(id).orElse(null);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new DoctorDTO(result));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<DoctorDTO> findByCrm(Long crm) {
        Doctor result = doctorRepository.findByCrm(crm);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new DoctorDTO(result));
    }

    @Transactional
    public ResponseEntity<DoctorDTO> create(NewDoctorDTO body) {
        Doctor exists = doctorRepository.findByCrm(body.getCrm());
        if (exists != null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Doctor newDoctor = new Doctor(body);
        doctorRepository.save(newDoctor);
        return ResponseEntity.ok(new DoctorDTO(newDoctor));
    }

    @Transactional
    public ResponseEntity<DoctorDTO> update(Long id, UpdateDoctorDTO body) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor == null)
            return ResponseEntity.notFound().build();
            
        doctor.update(body);
        doctorRepository.save(doctor);
        return ResponseEntity.ok(new DoctorDTO(doctor));
    }
}
