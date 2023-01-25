package com.stanford.springjwt.controllers;


import com.stanford.springjwt.dto.AuthoritiesDto;
import com.stanford.springjwt.models.Authorities;
import com.stanford.springjwt.service.AuthoritiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/authority")
public class AuthoritiesController {
    @Autowired
    AuthoritiesService authorityService;

    @PostMapping(path = "")
    public ResponseEntity<Long> createOrUpdateAuthorities(@RequestBody AuthoritiesDto authoritieyDto) {
        log.info("AuthoritiesController: createOrUpdateAuthorities");
        Authorities authoritiey = authorityService.saveOrUpdate(authoritieyDto);
        return new ResponseEntity<>(authoritiey.getId(), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<AuthoritiesDto>> getAuthorities() {
        log.info("AuthoritiesController: getAuthorities");
        List<AuthoritiesDto> authoritieies = authorityService.findAll();
        return new ResponseEntity<>(authoritieies, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AuthoritiesDto> getAuthorities(@PathVariable Long id) {
        log.info("AuthoritiesController: getAuthorities");
        AuthoritiesDto authoritiey = authorityService.findById(id);
        return new ResponseEntity<>(authoritiey, HttpStatus.OK);
    }
}