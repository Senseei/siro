package com.internacao.siro.services.register;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.internacao.siro.dto.register.NewRegisterDTO;
import com.internacao.siro.dto.register.RegisterDTO;
import com.internacao.siro.dto.register.UpdateRegisterDTO;
import com.internacao.siro.entities.Clinic;
import com.internacao.siro.entities.Doctor;
import com.internacao.siro.entities.Employee;
import com.internacao.siro.entities.Patient;
import com.internacao.siro.entities.Person;
import com.internacao.siro.entities.Register;
import com.internacao.siro.projections.RelativeProjection;
import com.internacao.siro.util.Util;

import jakarta.persistence.EntityNotFoundException;

@Component
public class RegisterUtil extends Util {

    public RegisterDTO createRegisterDTO(Register register) {
        if (register.getRelative() != null) {
                RelativeProjection relativeProjection = patientRepository.findRelativeById(
                    register.getRelative().getId(), register.getPatient().getId());
                return RegisterDTO.of(register, relativeProjection);
            }
            return RegisterDTO.of(register);
    }

    RegisterDTO createRegisterDTO(Patient patient) {
        Optional<Register> registerOpt = Optional.ofNullable(registerRepository.findByPatient(patient));

        return registerOpt.map(register -> {
            RelativeProjection relativeProjection = null;
            if (register.getRelative() != null) {
                relativeProjection = patientRepository.findRelativeById(register.getRelative().getId(), patient.getId());
            }
            return RegisterDTO.of(register, relativeProjection);
        }).orElse(null);
    }

    void updateDTOToEntity(Register register, UpdateRegisterDTO body) {

        validateJson(body);

        Patient patient = null;

        if (body.getPatientId() != null && register.getRelative() != null) {
            patientRepository.deleteRelative(register.getRelative().getId(), register.getPatient().getId());
            register.setRelative(null);
            patient = patientRepository.findById(body.getPatientId()).orElse(null);
        }
        else if (body.getPatientId() != null)
            patient = patientRepository.findById(body.getPatientId()).orElse(null);

        Doctor doctor = body.getDoctorId() != null ? doctorRepository.findById(body.getDoctorId()).orElse(null) : null;
        Clinic clinic = body.getClinicId() != null ? clinicRepository.findById(body.getClinicId()).orElse(null) : null;

        Person relative = null;
        if (body.getRelative() != null) {
            relative = personRepository.findById(body.getRelative().getId()).orElse(null);
            Long patientId = register.getPatient().getId();
            if (body.getPatientId() != null)
                patientId = body.getPatientId();
                
            patientRepository.addRelative(body.getRelative().getId(), patientId, body.getRelative().getRelationship());
        }

        Employee attendant = body.getEmployeeId() != null ? employeeRepository.findById(body.getEmployeeId()).orElse(null) : null;

        register.update(patient, body.getDateOfDeath(), doctor, clinic, relative, body.getDocumentationWithdrawal(), attendant);
    }

    void validateJson(UpdateRegisterDTO body) {

        validateEntityExistence(body.getPatientId(), patientRepository, "patient");
        validateEntityExistence(body.getDoctorId(), doctorRepository, "doctor");
        validateEntityExistence(body.getClinicId(), clinicRepository, "clinic");
        validateEntityExistence(body.getEmployeeId(), employeeRepository, "employee");
        if (body.getRelative() != null)
            validateEntityExistence(body.getRelative().getId(), personRepository, "relative");
    }

    void validateJson(NewRegisterDTO body) {
        validateJsonEntityField(body.getClinicId(), clinicRepository, "Clinic");
        validateJsonEntityField(body.getDoctorId(), doctorRepository, "Doctor");
        validateJsonEntityField(body.getPatientId(), patientRepository, "Patient");
    }

    public Register checkIfRegisterExists(Long id) {
        Register register = registerRepository.findById(id).orElse(null);
        
        if (register == null)
            throw new EntityNotFoundException("The register with the given Id does not exist");
        return register;
    }

    public Register checkIfRegisterExistsByMr(Long mr) {
        Patient patient = patientRepository.findByMr(mr);
        if (patient == null)
            throw new EntityNotFoundException("The patient with the given medical record does not exist");

        Register register = registerRepository.findByPatient(patient);
        if (register == null)
            throw new EntityNotFoundException("There is no register with this patient yet");

        return register;
    }
}
