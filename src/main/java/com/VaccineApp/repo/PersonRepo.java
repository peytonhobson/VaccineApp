package com.VaccineApp.repo;

import com.VaccineApp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person,Long> {

    void deletePersonById(Long id);
    Optional<Person> findPersonById(long id);
    Optional<Person> findPersonByName(String name);
}
