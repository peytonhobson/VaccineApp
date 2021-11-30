package com.VaccineApp.service;

import com.VaccineApp.model.Organization;
import com.VaccineApp.repo.OrganizationRepo;
import com.VaccineApp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrganizationService {
    private final OrganizationRepo organizationRepo;

    @Autowired
    public OrganizationService(OrganizationRepo organizationRepo) {
        this.organizationRepo = organizationRepo;
    }

    public Organization addOrganization(Organization organization) {
        return organizationRepo.save(organization);
    }

    public List<Organization> findAllOrganizations() {
        return organizationRepo.findAll();
    }

    public Organization updateOrganization(Organization organization) {
        return organizationRepo.save(organization);
    }

    public Organization findOrganizationByID(int id) throws Throwable {
        return organizationRepo.findOrganizationById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public Organization findOrganizationByNAME(String name)  {
        return organizationRepo.findOrganizationByName(name)
                .orElseThrow(() -> new UserNotFoundException("User by id " + name + " was not found"));
    }

    public void deleteOrganization(int id) { organizationRepo.deleteOrganizationById(id); }
}