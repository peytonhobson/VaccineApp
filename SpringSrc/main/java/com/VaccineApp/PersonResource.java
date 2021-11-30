package com.VaccineApp;


import com.VaccineApp.model.Person;
import com.VaccineApp.service.PersonService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonResource {
    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.findAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") int id) throws Throwable {
        Person person = personService.findPersonByID(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/findName/{name}")
    public ResponseEntity<Person> getPersonByName(@PathVariable("name") String name) throws Throwable {
        Person person = personService.findPersonByNAME(name);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person newPerson = personService.addPerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Person> updateEmployee(@RequestBody Person person) {
        Person updatePerson = personService.updatePerson(person);
        return new ResponseEntity<>(updatePerson, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") int id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
