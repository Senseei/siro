package com.internacao.siro.services.register;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
import com.internacao.siro.repositories.ClinicRepository;
import com.internacao.siro.repositories.DoctorRepository;
import com.internacao.siro.repositories.EmployeeRepository;
import com.internacao.siro.repositories.PatientRepository;
import com.internacao.siro.repositories.PersonRepository;
import com.internacao.siro.repositories.RegisterRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class RegisterUtil {
    
    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    ClinicRepository clinicRepository;

    public RegisterDTO createRegisterDTO(Register register) {
        if (register.getRelative() != null) {
                RelativeProjection relativeProjection = patientRepository.findRelativeById(
                    register.getRelative().getId(), register.getPatient().getId());
                return RegisterDTO.of(register, relativeProjection);
            }
            return RegisterDTO.of(register);
    }

    public RegisterDTO createRegisterDTO(Patient patient) {
        Optional<Register> registerOpt = Optional.ofNullable(registerRepository.findByPatient(patient));

        return registerOpt.map(register -> {
            RelativeProjection relativeProjection = null;
            if (register.getRelative() != null) {
                relativeProjection = patientRepository.findRelativeById(register.getRelative().getId(), patient.getId());
            }
            return RegisterDTO.of(register, relativeProjection);
        }).orElse(null);
    }

    public void updateDTOToEntity(Register register, UpdateRegisterDTO body) {

        checkUpdateRegisterDTO(body);

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
            patientRepository.addRelative(body.getRelative().getId(), body.getPatientId(), body.getRelative().getRelationship());
        }

        Employee attendant = body.getEmployeeId() != null ? employeeRepository.findById(body.getEmployeeId()).orElse(null) : null;

        register.update(patient, body.getDateOfDeath(), doctor, clinic, relative, body.getDocumentationWithdrawal(), attendant);
    }

    private void checkUpdateRegisterDTO(UpdateRegisterDTO body) {

        validateEntityExistence(body.getPatientId(), patientRepository, "patient");
        validateEntityExistence(body.getDoctorId(), doctorRepository, "doctor");
        validateEntityExistence(body.getClinicId(), clinicRepository, "clinic");
        validateEntityExistence(body.getEmployeeId(), employeeRepository, "employee");
        if (body.getRelative() != null)
            validateEntityExistence(body.getRelative().getId(), personRepository, "relative");
    }

    public void checkNewRegisterBody(NewRegisterDTO body) {
        checkNewRegisterBody(body.getClinicId(), clinicRepository, "Clinic");
        checkNewRegisterBody(body.getDoctorId(), doctorRepository, "Doctor");
        checkNewRegisterBody(body.getPatientId(), patientRepository, "Patient");
    }

    public void checkNewRegisterBody(Long entityId, JpaRepository<?, Long> repository, String entityName) {
        if (entityId == null)
            throw new IllegalArgumentException(entityName + " Id cannot be null");
        validateEntityExistence(entityId, repository, entityName);
    }

    private void validateEntityExistence(Long entityId, JpaRepository<?, Long> repository, String entityName) {
        if (entityId != null && !repository.existsById(entityId)) {
            throw new EntityNotFoundException("The " + entityName + " with the given Id doesn't exist");
        }
    }
}
