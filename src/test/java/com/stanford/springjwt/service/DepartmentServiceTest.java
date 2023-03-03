package com.stanford.springjwt.service;

import com.stanford.springjwt.dto.DepartmentDto;
import com.stanford.springjwt.dto.OrganizationDto;
import com.stanford.springjwt.models.Department;
import com.stanford.springjwt.models.Organization;
import com.stanford.springjwt.repository.DepartmentRepository;
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
class DepartmentServiceTest {

    @InjectMocks
    DepartmentService departmentService;
    @Mock
    DepartmentRepository departmentRepository;
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
        List<Department> repo = new ArrayList<>();
        Department oneD = new Department();
        oneD.setId(id);
        repo.add(oneD);
        when(departmentRepository.findAll()).thenReturn(repo);
        List<DepartmentDto> all = departmentService.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void findById() {
        Long id = 1L;
        Department one = new Department();
        one.setId(id);
        when(departmentRepository.findById(id)).thenReturn(Optional.of(one));
        DepartmentDto all = departmentService.findById(id);
        assertEquals(1, all.getId());
    }



}
