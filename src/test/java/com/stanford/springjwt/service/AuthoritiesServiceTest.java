package com.stanford.springjwt.service;

import com.stanford.springjwt.dto.AuthoritiesDto;
import com.stanford.springjwt.models.Authorities;
import com.stanford.springjwt.repository.AuthoritiesRepository;
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
class AuthoritiesServiceTest {

    @InjectMocks
    AuthoritiesService authoritiesService;
    @Mock
    AuthoritiesRepository authoritiesRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void findAll() {
        List<Authorities> repo = new ArrayList<>();
        Authorities one = new Authorities();
        repo.add(one);
        when(authoritiesRepository.findAll()).thenReturn(repo);
        List<AuthoritiesDto> all = authoritiesService.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void findById() {
        Long id = 1L;
        Authorities one = new Authorities();
        one.setId(id);
        when(authoritiesRepository.findById(id)).thenReturn(Optional.of(one));
        AuthoritiesDto all = authoritiesService.findById(id);
        assertEquals(1, all.getId());
    }


    @Test
    void saveOrUpdate() {
    }
}