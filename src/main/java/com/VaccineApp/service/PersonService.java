package com.VaccineApp.service;

import com.VaccineApp.model.Person;
import com.VaccineApp.repo.PersonRepo;
import com.VaccineApp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Person addPerson(Person person) {
        return personRepo.save(person);
    }

    public List<Person> findAllPersons() {
        return personRepo.findAll();
    }

    public Person updatePerson(Person person) {
        return personRepo.save(person);
    }

    public Person findPersonByID(int id) throws Throwable {
        return personRepo.findPersonById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public Person findPersonByNAME(String name)  {
        return personRepo.findPersonByName(name)
                .orElseThrow(() -> new UserNotFoundException("User by id " + name + " was not found"));
    }

    public void deletePerson(int id) {
        personRepo.deletePersonById(id);
    }
}
