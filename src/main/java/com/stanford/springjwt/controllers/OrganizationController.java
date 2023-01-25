package com.stanford.springjwt.controllers;


import com.stanford.springjwt.dto.OrganizationDto;
import com.stanford.springjwt.models.Organization;
import com.stanford.springjwt.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(path = "/org")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @PostMapping(path = "/many")
    public ResponseEntity<Long> createOrUpdateOrganizationMany(@RequestBody List<OrganizationDto> organizationDto) {
        log.info("OrganizationController: createOrUpdateOrganization");
        Organization organization = new Organization();
        for(OrganizationDto o: organizationDto) {
            organization = organizationService.saveOrUpdate(o);
        }
        return new ResponseEntity<>(organization.getId(), HttpStatus.OK);
    }
    @PostMapping(path = "")
    public ResponseEntity<Long> createOrUpdateOrganization(@RequestBody OrganizationDto organizationDto) {
        log.info("OrganizationController: createOrUpdateOrganization");
        Organization organization = organizationService.saveOrUpdate(organizationDto);
        return new ResponseEntity<>(organization.getId(), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<OrganizationDto>> getOrganizations() {
        log.info("OrganizationController: get");
        List<OrganizationDto> organizationServiceAll = organizationService.findAll();
        return new ResponseEntity<>(organizationServiceAll, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable Long id) {
        log.info("OrganizationController: getOrganization");
        OrganizationDto organization = organizationService.findById(id);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }
}