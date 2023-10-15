package com.internacao.siro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internacao.siro.dto.clinic.ClinicDTO;
import com.internacao.siro.dto.clinic.NewClinicDTO;
import com.internacao.siro.dto.clinic.UpdateClinicDTO;
import com.internacao.siro.entities.Clinic;
import com.internacao.siro.repositories.ClinicRepository;
import com.internacao.siro.validators.json.ClinicJson;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClinicService {

    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    ClinicJson clinicJson;

    @Transactional(readOnly = true)
    public List<ClinicDTO> findAll() {
        List<Clinic> result = clinicRepository.findAll();
        return result.stream().map(x -> ClinicDTO.of(x)).toList();
    }

    @Transactional(readOnly = true)
    public ClinicDTO findById(Long id) {
        Clinic clinic = clinicRepository.findById(id).orElse(null);
        if (clinic == null)
            throw new EntityNotFoundException();
        return ClinicDTO.of(clinic);
    }

    @Transactional(readOnly = true)
    public ClinicDTO findByName(String name) {
        Clinic clinic = clinicRepository.findByName(name);
        if (clinic == null)
            throw new EntityNotFoundException();
        return ClinicDTO.of(clinic);
    }

    @Transactional
    public ClinicDTO create(NewClinicDTO body) {
        if (clinicRepository.existsByName(body.getName()))
            throw new DuplicateKeyException("There already exists a clinic with this name");

        clinicJson.validate(body);

        Clinic clinic = new Clinic(body);
        clinicRepository.save(clinic);

        return ClinicDTO.of(clinic);
    }

    @Transactional
    public ClinicDTO update(UpdateClinicDTO body, Long id) {
        Clinic clinic = clinicRepository.findById(id).orElse(null);
        if (clinic == null)
            throw new EntityNotFoundException();

        clinicJson.validate(body);

        clinic.update(body);
        clinicRepository.save(clinic);
        return ClinicDTO.of(clinic);
    }
}
