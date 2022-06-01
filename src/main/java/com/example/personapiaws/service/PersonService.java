package com.example.personapiaws.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.example.personapiaws.exception.BadRequestException;
import com.example.personapiaws.model.Person;
import com.example.personapiaws.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	
	private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Page<Person> listAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }
    public List<Person> listAllNonPageable() {
        return personRepository.findAll();
    }
    public List<Person> findByFirstName(String name){
        return personRepository.findByFirstName(name);
    }

    public Person findByIdOrThrowBadRequestException(long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Person not found"));
    }

    @Transactional
    public Person save(Person person) {
        person.setDateCreated(new Date());
        return personRepository.save(person);
    }

    public void delete(long id) {
        personRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(Person person) {
        Person savedPerson = findByIdOrThrowBadRequestException(person.getId());
        person.setId(savedPerson.getId());
        person.setDateCreated(savedPerson.getDateCreated());
        personRepository.save(person);
    }

}
