package com.example.personapiaws.repository;

import com.example.personapiaws.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByFirstName(String name);
}
