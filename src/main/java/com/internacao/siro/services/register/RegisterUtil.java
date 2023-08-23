package com.internacao.siro.services.register;

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
import com.internacao.siro.entities.Relative;
import com.internacao.siro.entities.RelativeId;
import com.internacao.siro.util.Util;

import jakarta.persistence.EntityNotFoundException;

@Component
public class RegisterUtil extends Util {

    RegisterDTO createRegisterDTO(Register register) {
        Relative relative = relativeRepository.findById(new RelativeId(register.getRelative(), register.getPatient())).orElse(null);
        return RegisterDTO.of(register, relative);
    }

    void updateDTOToEntity(Register register, UpdateRegisterDTO body) {

        validateJson(body);

        Patient patient = setPatient(register, body);
        Doctor doctor = body.getDoctorId() != null ? doctorRepository.findById(body.getDoctorId()).orElse(null) : null;
        Clinic clinic = body.getClinicId() != null ? clinicRepository.findById(body.getClinicId()).orElse(null) : null;
        Person relative = setRelative(register, body);
        Employee attendant = body.getEmployeeId() != null ? employeeRepository.findById(body.getEmployeeId()).orElse(null) : null;

        register.update(patient, body.getDateOfDeath(), doctor, clinic, relative, body.getDocumentationWithdrawal(), attendant);
    }

    private Patient setPatient(Register register, UpdateRegisterDTO body) {
        if (body.getPatientId() == null) {
            return null;
        }
        register.setRelative(null);
        return patientRepository.findById(body.getPatientId()).orElse(null);
    }

    private Person setRelative(Register register, UpdateRegisterDTO body) {
        if (body.getRelative() == null) {
            return null;
        }
        Person relative = personRepository.findById(body.getRelative().getId()).orElse(null);
        Patient patient = body.getPatientId() == null ? register.getPatient() : patientRepository.findById(body.getPatientId()).orElse(null);
        Relative relativeEntity = new Relative(relative, patient, body.getRelative().getRelationship());
        relativeRepository.save(relativeEntity);
        return relative;
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

    public Register checkIfRegisterExistsByPatientId(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null)
            throw new EntityNotFoundException("The patient with the given Id does not exist");

        Register register = registerRepository.findByPatient(patient);
        if (register == null)
            throw new EntityNotFoundException("There is no register with this patient yet");

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
