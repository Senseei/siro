package com.internacao.siro.services;

import java.time.LocalDateTime;
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
        List<Doctor> result = doctorRepository.findByDeletedAtIsNull();
        List<DoctorDTO> dto = result.stream().map(x -> new DoctorDTO(x)).toList();
        return dto;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<DoctorDTO> findById(Long id) {
        Doctor result = doctorRepository.findByIdAndDeletedAtIsNull(id);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new DoctorDTO(result));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<DoctorDTO> findByCrm(Long crm) {
        Doctor result = doctorRepository.findByCrmAndDeletedAtIsNull(crm);
        if (result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new DoctorDTO(result));
    }

    @Transactional
    public ResponseEntity<DoctorDTO> createNewDoctor(NewDoctorDTO body) {
        Doctor exists = doctorRepository.findByCrm(body.getCrm());
        if (exists != null && exists.getDeletedAt() != null) {
            exists.reverseDelete(body);
            doctorRepository.save(exists);
            return ResponseEntity.ok(new DoctorDTO(exists));
        }
        else if (exists != null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Doctor newDoctor = new Doctor(body);
        doctorRepository.save(newDoctor);
        return ResponseEntity.ok(new DoctorDTO(newDoctor));
    }

    @Transactional
    public ResponseEntity<DoctorDTO> updateDoctor(Long id, UpdateDoctorDTO body) {
        Doctor doctor = doctorRepository.findByIdAndDeletedAtIsNull(id);
        if (doctor == null)
            return ResponseEntity.notFound().build();
            
        doctor.updateDoctor(body);
        doctorRepository.save(doctor);
        return ResponseEntity.ok(new DoctorDTO(doctor));
    }

    @Transactional
    public ResponseEntity<String> deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findByIdAndDeletedAtIsNull(id);
        if (doctor == null)
            return ResponseEntity.notFound().build();
            
        doctor.setDeletedAt(LocalDateTime.now());
        return ResponseEntity.noContent().build();
    }
}
