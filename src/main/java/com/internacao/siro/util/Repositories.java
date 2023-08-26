package com.internacao.siro.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.internacao.siro.repositories.ClinicRepository;
import com.internacao.siro.repositories.DoctorRepository;
import com.internacao.siro.repositories.EmployeeRepository;
import com.internacao.siro.repositories.PatientRepository;
import com.internacao.siro.repositories.PersonRepository;
import com.internacao.siro.repositories.RegisterRepository;
import com.internacao.siro.repositories.RelativeRepository;

@Component
public class Repositories {
    
    @Autowired
    public RegisterRepository registerRepository;
    @Autowired
    public PersonRepository personRepository;
    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    public PatientRepository patientRepository;
    @Autowired
    public DoctorRepository doctorRepository;
    @Autowired
    public ClinicRepository clinicRepository;
    @Autowired
    public RelativeRepository relativeRepository;
}
