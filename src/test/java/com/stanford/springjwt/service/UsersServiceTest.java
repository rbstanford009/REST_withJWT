package com.stanford.springjwt.service;

import com.stanford.springjwt.dto.UsersDto;
import com.stanford.springjwt.models.UsersProfile;
import com.stanford.springjwt.repository.UsersProfileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @InjectMocks
    UsersService usersService;
    @Mock
    UsersProfileRepository usersRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        List<UsersProfile> repo = new ArrayList<>();
        UsersProfile one = new UsersProfile();
        one.setLast_name("last");
        repo.add(one);
        when(usersRepository.findAll()).thenReturn(repo);
        List<UsersDto> all = usersService.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void findById() {
        Long id = 1L;
        UsersProfile one = new UsersProfile();
        one.setLast_name("last");
        one.setId(id);
        when(usersRepository.findById(id)).thenReturn(Optional.of(one));
        UsersDto all = usersService.findById(id);
        assertEquals(1, all.getId());
    }

    @Test
    void saveOrUpdate() {
        Long id = 1L;
        UsersProfile one = new UsersProfile();
        one.setLast_name("last");
        one.setId(id);
        when(usersRepository.save(Mockito.any())).thenReturn(one);
        UsersDto usersDto = new UsersDto();
        usersDto.setId(1l);
        usersDto.setPassword("pass");
        usersDto.setUsername("Uname");
        usersDto.setFirst_name("First");
        usersDto.setLast_name("Last");
        UsersProfile all = usersService.saveOrUpdate(usersDto);
        assertEquals("last", all.getLast_name());
    }


    @Test
    public void simple() {
        List<String> myList = new ArrayList<String>();
        myList.add("Hello, This is Test");
        assertEquals(1, myList.size());
    }
}
