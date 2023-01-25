package com.stanford.springjwt.controllers;


import com.stanford.springjwt.dto.UsersDto;
import com.stanford.springjwt.models.UsersProfile;
import com.stanford.springjwt.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/user")
public class UsersController {
    @Autowired
    UsersService usersService;

    @PostMapping(path = "")
    public ResponseEntity<Long> createOrUpdateUsers(@RequestBody UsersDto usersDto) {
        log.info("UsersController: createOrUpdateUsers");
        UsersProfile users = usersService.saveOrUpdate(usersDto);
        return new ResponseEntity<>(users.getId(), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<UsersDto>> getUsers() {
        log.info("UsersController: getUsers");
        List<UsersDto> usersies = usersService.findAll();
        return new ResponseEntity<>(usersies, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsersDto> getUsers(@PathVariable Long id) {
        log.info("UsersController: getUsers");
        UsersDto users = usersService.findById(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}