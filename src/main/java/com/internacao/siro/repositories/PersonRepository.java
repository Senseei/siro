package com.internacao.siro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internacao.siro.entities.Person;


public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByDeletedAtIsNull();
    Person findByIdAndDeletedAtIsNull(Long id);
    Person findByCpf(String cpf);
}
