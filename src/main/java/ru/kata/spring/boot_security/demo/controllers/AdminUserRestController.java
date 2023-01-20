package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.services.PersonService;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminUserRestController {

    private final PersonService personService;

    public AdminUserRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<Person>> showAllPersons() {
        List<Person> allPersons = personService.getAllPersons();
        return new ResponseEntity<>(allPersons, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }


    @PostMapping("/users")
    public ResponseEntity<Person> addNewPerson(@RequestBody Person person) {
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        personService.savePerson(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        personService.updatePerson(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return "Person with id =" + id + " was deleted";
    }
}


