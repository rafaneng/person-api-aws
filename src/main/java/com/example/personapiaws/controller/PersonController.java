package com.example.personapiaws.controller;

import com.example.personapiaws.model.Person;
import com.example.personapiaws.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping()
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<Page<Person>> list(Pageable pageable){
        return new ResponseEntity<>(personService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Person>> listAll(){
        return new ResponseEntity<>(personService.listAllNonPageable(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Person> findById(@PathVariable long id){
        // Forma 2 de enviar http status code
        return ResponseEntity.ok(personService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Person>> findByFirstName(@RequestParam String name){
        return ResponseEntity.ok(personService.findByFirstName(name));
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody @Valid Person person){
        return new ResponseEntity<>(personService.save(person), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Person person){
        personService.replace(person);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
