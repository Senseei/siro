package com.internacao.siro.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public DoctorDTO findById(Long id) {
        Doctor result = doctorRepository.findByIdAndDeletedAtIsNull(id);
        return new DoctorDTO(result);
    }

    @Transactional(readOnly = true)
    public DoctorDTO findByCrm(Long crm) {
        Doctor result = doctorRepository.findByCrmAndDeletedAtIsNull(crm);
        return new DoctorDTO(result);
    }

    @Transactional
    public DoctorDTO createNewDoctor(NewDoctorDTO dto) {
        Doctor exists = doctorRepository.findByCrm(dto.getCrm());
        if (exists != null && exists.getDeletedAt() != null) {
            exists.reverseDelete(dto);
            doctorRepository.save(exists);
            return new DoctorDTO(exists);
        }
        Doctor newDoctor = new Doctor(dto);
        doctorRepository.save(newDoctor);
        return new DoctorDTO(newDoctor);
    }

    @Transactional
    public DoctorDTO updateDoctor(Long id, UpdateDoctorDTO body) {
        Doctor doctor = doctorRepository.findByIdAndDeletedAtIsNull(id);
        if (doctor != null) {
            doctor.updateDoctor(body);
            doctorRepository.save(doctor);
        }
        return new DoctorDTO(doctor);
    }

    @Transactional
    public ResponseEntity<String> deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findByIdAndDeletedAtIsNull(id);
        if (doctor == null) {
            return ResponseEntity.notFound().build();
        }
        doctor.setDeletedAt(LocalDateTime.now());
        return ResponseEntity.noContent().build();
    }
}
