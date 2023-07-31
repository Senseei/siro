package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.DoctorDTO;
import com.internacao.siro.entities.Doctor;
import com.internacao.siro.projections.DoctorProjection;
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
    public DoctorDTO findByCRM(Long crm) {
        DoctorProjection result = doctorRepository.findByCRM(crm);
        return new DoctorDTO(result);
    }

}
