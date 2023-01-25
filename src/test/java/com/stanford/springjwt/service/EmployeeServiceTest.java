package com.stanford.springjwt.service;

import com.stanford.springjwt.dto.EmployeeDto;
import com.stanford.springjwt.models.Employee;
import com.stanford.springjwt.repository.EmployeeRepository;
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
class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;
    @Mock
    EmployeeRepository employeeRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void findAll() {
        List<Employee> repo = new ArrayList<>();
        Employee one = new Employee();
        repo.add(one);
        when(employeeRepository.findAll()).thenReturn(repo);
        List<EmployeeDto> all = employeeService.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void findById() {
        Long id = 1L;
        Employee one = new Employee();
        one.setId(id);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(one));
        EmployeeDto all = employeeService.findById(id);
        assertEquals(1, all.getId());
    }


    @Test
    void saveOrUpdate() {
    }
}