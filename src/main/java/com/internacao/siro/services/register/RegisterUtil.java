package com.internacao.siro.services.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.internacao.siro.dto.register.RegisterDTO;
import com.internacao.siro.dto.register.UpdateRegisterDTO;
import com.internacao.siro.entities.Clinic;
import com.internacao.siro.entities.Doctor;
import com.internacao.siro.entities.Employee;
import com.internacao.siro.entities.Patient;
import com.internacao.siro.entities.Person;
import com.internacao.siro.entities.Register;
import com.internacao.siro.entities.Relative;
import com.internacao.siro.entities.RelativeId;
import com.internacao.siro.util.Json;
import com.internacao.siro.util.Repositories;

import jakarta.persistence.EntityNotFoundException;

@Component
public class RegisterUtil {

    @Autowired
    Repositories repositories;
    @Autowired
    Json json;


    RegisterDTO createRegisterDTO(Register register) {
        Relative relative = repositories.relativeRepository.findById(new RelativeId(register.getRelative(), register.getPatient())).orElse(null);
        return RegisterDTO.of(register, relative);
    }

    void updateDTOToEntity(Register register, UpdateRegisterDTO body) {

        json.validate(body);

        Patient patient = setPatient(register, body);
        Doctor doctor = body.getDoctorId() != null ? repositories.doctorRepository.findById(body.getDoctorId()).orElse(null) : null;
        Clinic clinic = body.getClinicId() != null ? repositories.clinicRepository.findById(body.getClinicId()).orElse(null) : null;
        Person relative = setRelative(register, body);
        Employee attendant = body.getEmployeeId() != null ? repositories.employeeRepository.findById(body.getEmployeeId()).orElse(null) : null;

        register.update(patient, body.getDateOfDeath(), doctor, clinic, relative, body.getDocumentationWithdrawal(), attendant);
    }

    private Patient setPatient(Register register, UpdateRegisterDTO body) {
        if (body.getPatientId() == null) {
            return null;
        }
        register.setRelative(null);
        return repositories.patientRepository.findById(body.getPatientId()).orElse(null);
    }

    private Person setRelative(Register register, UpdateRegisterDTO body) {
        if (body.getRelative() == null) {
            return null;
        }
        Person relative = repositories.personRepository.findById(body.getRelative().getId()).orElse(null);
        Patient patient = body.getPatientId() == null ? register.getPatient() : repositories.patientRepository.findById(body.getPatientId()).orElse(null);
        Relative relativeEntity = new Relative(relative, patient, body.getRelative().getRelationship());
        repositories.relativeRepository.save(relativeEntity);
        return relative;
    }

    public Register checkIfRegisterExists(Long id) {
        Register register = repositories.registerRepository.findById(id).orElse(null);
        
        if (register == null)
            throw new EntityNotFoundException("The register with the given Id does not exist");
        return register;
    }

    public Register checkIfRegisterExistsByPatientId(Long id) {
        Patient patient = repositories.patientRepository.findById(id).orElse(null);
        if (patient == null)
            throw new EntityNotFoundException("The patient with the given Id does not exist");

        Register register = repositories.registerRepository.findByPatient(patient);
        if (register == null)
            throw new EntityNotFoundException("There is no register with this patient yet");

        return register;
    }

    public Register checkIfRegisterExistsByMr(Long mr) {
        Patient patient = repositories.patientRepository.findByMr(mr);
        if (patient == null)
            throw new EntityNotFoundException("The patient with the given medical record does not exist");

        Register register = repositories.registerRepository.findByPatient(patient);
        if (register == null)
            throw new EntityNotFoundException("There is no register with this patient yet");

        return register;
    }
}
