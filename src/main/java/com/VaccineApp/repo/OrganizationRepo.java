package com.VaccineApp.repo;

import com.VaccineApp.model.Organization;
import com.VaccineApp.model.Person;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepo extends JpaRepository<Organization,Long> {

    void deleteOrganizationById(int id);
    Optional<Organization> findOrganizationById(int id);
    Optional<Organization> findOrganizationByName(String name);
}
