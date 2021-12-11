package com.luxoft.data.examples.controllers.hateoas;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/models")
public class PersonModelController {

    @GetMapping("{id}")
    public PersonModel getModel(@PathVariable Long id)
    {
        PersonModel model = new PersonModel();
        model.setFirstname("Dave");
        model.setLastname("Matthews");
        model.add(Link.of("https://localhost:8080/models/" + id));

        return model;
    }
}
