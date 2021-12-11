package com.luxoft.data.examples.controllers;

import com.luxoft.data.examples.controllers.assemblers.PersonModelAssembler;
import com.luxoft.data.examples.exceptions.PersonNotFoundException;
import com.luxoft.data.examples.model.Person;
import com.luxoft.data.examples.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
public class HateoasPersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonModelAssembler personModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Person>> findAll() {
        return personModelAssembler.toCollectionModel(personRepository.findAll());
    }

    @GetMapping("/{id}")
    public EntityModel<Person> findById(@PathVariable Long id) {

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return personModelAssembler.toModel(person);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleExceptions(PersonNotFoundException e)
    {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
