package com.VaccineApp.repo;

import com.VaccineApp.enumeration.OrganizationType;
import com.VaccineApp.model.Organization;

import java.util.List;


public interface OrganizationRepo {

    String deleteOrganizationByUsername(String username);
    Organization findOrganizationByUsername(String username);
    Organization updateOrganizationByUsername(String username);
    List<Organization> findOrganizationByType(OrganizationType type);
    List<Organization> findOrganizationByName(String name);
}
