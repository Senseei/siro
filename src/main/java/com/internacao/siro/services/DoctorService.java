package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.doctor.DoctorMinDTO;
import com.internacao.siro.dto.doctor.NewDoctorDTO;
import com.internacao.siro.entities.Doctor;
import com.internacao.siro.repositories.DoctorRepository;

@Service
public class DoctorService {
    
    @Autowired
    DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public List<DoctorMinDTO> findAll() {
        List<Doctor> result = doctorRepository.findAll();
        List<DoctorMinDTO> dto = result.stream().map(x -> new DoctorMinDTO(x)).toList();
        return dto;
    }

    @Transactional(readOnly = true)
    public DoctorMinDTO findById(Long id) {
        Doctor result = doctorRepository.findById(id).orElse(null);
        return new DoctorMinDTO(result);
    }

    @Transactional(readOnly = true)
    public DoctorMinDTO findByCrm(Long crm) {
        Doctor result = doctorRepository.findByCrm(crm);
        return new DoctorMinDTO(result);
    }

    @Transactional
    public DoctorMinDTO createNewDoctor(NewDoctorDTO dto) {
        Doctor newDoctor = new Doctor(dto);
        doctorRepository.save(newDoctor);
        return new DoctorMinDTO(newDoctor);
    }

}
