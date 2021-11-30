package com.VaccineApp;


import com.VaccineApp.model.Organization;
import com.VaccineApp.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization")
public class OrganizationResource {
    private final OrganizationService organizationService;

    public OrganizationResource(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Organization>> getAllOrganizations() {
        List<Organization> organizations = organizationService.findAllOrganizations();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable("id") int id) throws Throwable {
        Organization organization = organizationService.findOrganizationByID(id);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @GetMapping("/findName/{name}")
    public ResponseEntity<Organization> getOrganizationByName(@PathVariable("name") String name) throws Throwable {
        Organization organization = organizationService.findOrganizationByNAME(name);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Organization> addOrganization(@RequestBody Organization organization) {
        Organization newOrganization = organizationService.addOrganization(organization);
        return new ResponseEntity<>(newOrganization, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Organization> updateOrganization(@RequestBody Organization organization) {
        Organization updateOrganization = organizationService.updateOrganization(organization);
        return new ResponseEntity<>(updateOrganization, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrganization(@PathVariable("id") int id) {
        organizationService.deleteOrganization(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}