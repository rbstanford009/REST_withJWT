package com.stanford.springjwt.service;

import com.stanford.springjwt.dto.OrganizationDto;
import com.stanford.springjwt.models.Organization;
import com.stanford.springjwt.repository.OrganizationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class OrganizationServiceTest {

    @InjectMocks
    OrganizationService organizationService;
    @Mock
    OrganizationRepository organizationRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        Long id = 1L;
        List<Organization> repo = new ArrayList<>();
        Organization one = new Organization();
        one.setId(id);
        repo.add(one);
        when(organizationRepository.findAll()).thenReturn(repo);
        List<OrganizationDto> all = organizationService.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void findById() {
        Long id = 1L;
        Organization one = new Organization();
        one.setId(id);
        when(organizationRepository.findById(id)).thenReturn(Optional.of(one));
        Optional<Organization> all = organizationRepository.findById(id);
        assertEquals(1, all.get().getId());
    }


}
