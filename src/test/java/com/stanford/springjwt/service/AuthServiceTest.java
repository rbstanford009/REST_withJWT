package com.stanford.springjwt.service;

import com.stanford.springjwt.dto.OrganizationDto;
import com.stanford.springjwt.models.ERole;
import com.stanford.springjwt.models.Organization;
import com.stanford.springjwt.models.Role;
import com.stanford.springjwt.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    AuthService authService;
    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    AuthenticationManager authenticationManager;


    @Mock
    OrganizationRepository organizationRepository;

    @Mock
    DepartmentRepository departmentRepository;

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
    void setupH2DatabaseTest() {
        Role repo = new Role();
        repo.setName(ERole.ROLE_USER);
        repo.setId(1);

  //      when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(repo));
        when(organizationRepository.save(Mockito.any())).thenReturn(null);
        when(departmentRepository.save(Mockito.any())).thenReturn(null);
        when(roleRepository.save(Mockito.any())).thenReturn(Mockito.any());
        authService.setupH2Database();
    }

    @Test
    void setupUserH2DatabaseTest() {
        Role repo = new Role();
        repo.setName(ERole.ROLE_USER);
        repo.setId(1);

        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(repo));

        when(userRepository.save(Mockito.any())).thenReturn(null);
        when(employeeRepository.save(Mockito.any())).thenReturn(null);
        authService.setupUserH2Database();
    }

}

