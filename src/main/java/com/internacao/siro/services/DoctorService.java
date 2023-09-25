package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
        return result.stream().map(x -> DoctorDTO.of(x)).toList();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<DoctorDTO> findById(Long id) {
        Doctor result = doctorRepository.findById(id).orElse(null);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(DoctorDTO.of(result));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<DoctorDTO> findByCrm(Long crm) {
        Doctor result = doctorRepository.findByCrm(crm);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(DoctorDTO.of(result));
    }

    @Transactional
    public ResponseEntity<DoctorDTO> create(NewDoctorDTO body) {
        if (doctorRepository.existsByCrm(body.getCrm()))
            throw new DuplicateKeyException("There is already a doctor with this CRM");

        Doctor newDoctor = new Doctor(body);
        doctorRepository.save(newDoctor);
        return ResponseEntity.ok(DoctorDTO.of(newDoctor));
    }

    @Transactional
    public ResponseEntity<DoctorDTO> update(Long id, UpdateDoctorDTO body) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor == null)
            return ResponseEntity.notFound().build();
            
        doctor.update(body);
        doctorRepository.save(doctor);
        return ResponseEntity.ok(DoctorDTO.of(doctor));
    }
}
