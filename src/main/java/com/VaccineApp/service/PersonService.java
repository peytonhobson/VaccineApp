package com.VaccineApp.service;

import com.VaccineApp.model.Person;
import com.VaccineApp.repo.PersonRepo;
import com.VaccineApp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.Random;

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

    public List<Person> findAllEmployees() {
        return personRepo.findAll();
    }

    public Person updateEmployee(Person employee) {
        return personRepo.save(employee);
    }

    public Person findEmployeeByID(long id) throws Throwable {
        return personRepo.findPersonById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteEmployee(Long id) {
        personRepo.deletePersonById(id);
    }
}
