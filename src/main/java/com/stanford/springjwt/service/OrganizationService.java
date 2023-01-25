package com.stanford.springjwt.service;
// DONE

import com.stanford.springjwt.dto.OrganizationDto;
import com.stanford.springjwt.models.Department;
import com.stanford.springjwt.models.Organization;
import com.stanford.springjwt.repository.DepartmentRepository;
import com.stanford.springjwt.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
public class OrganizationService {
    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public List<OrganizationDto> findAll() {
        log.info("OrganizationService: findAll");
        List<Organization> repo = organizationRepository.findAll();
        List<OrganizationDto> organizationDtos = new ArrayList<>();
        for (Organization organization : repo) {
            OrganizationDto organizationDto = new OrganizationDto();
            copyProperties(organization, organizationDto);
            organizationDtos.add(organizationDto);
        }
        return organizationDtos;
    }


    public OrganizationDto findById(Long id) {
        log.info("OrganizationService: findById");
        Organization organization = organizationRepository.findById(id).orElse(null);
        OrganizationDto organizationDto = new OrganizationDto();
        copyProperties(organization, organizationDto);
        return organizationDto;
    }


    public Organization saveOrUpdate(OrganizationDto organizationDto) {
        log.info("OrganizationService: saveOrUpdate, {}", organizationDto.getDescription());
        Organization organizationCPTest = new Organization();
        copyProperties(organizationDto, organizationCPTest);

        Department department = new Department();

        department.setDescription(organizationDto.getDescription());
        department.setDepartment_name(organizationDto.getOrg_name());
        department.setUpdated(new Timestamp(System.currentTimeMillis()));
        departmentRepository.save(department);

        Organization organization = new Organization();
        organization.setDescription(organizationDto.getDescription());
        organization.setRoot(organizationDto.getRoot());
        organization.setOrg_name(organizationDto.getOrg_name());
        organization.setUpdated(new Timestamp(System.currentTimeMillis()));


        return organizationRepository.save(organization);
    }
}