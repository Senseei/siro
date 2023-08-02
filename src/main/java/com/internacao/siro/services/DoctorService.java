package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.DoctorDTO;
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
    public DoctorDTO findById(Long id) {
        Doctor result = doctorRepository.findById(id).orElse(null);
        return new DoctorDTO(result);
    }

    @Transactional(readOnly = true)
    public DoctorDTO findByCrm(Long crm) {
        Doctor result = doctorRepository.findByCrm(crm);
        return new DoctorDTO(result);
    }

}
