package com.internacao.siro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internacao.siro.entities.Person;


public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByCpf(String cpf);
}
