package com.stanford.springjwt.controllers;


import com.stanford.springjwt.dto.DepartmentDto;
import com.stanford.springjwt.models.Department;
import com.stanford.springjwt.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping(path = "")
    public ResponseEntity<Long> createOrUpdateDepartment(@RequestBody DepartmentDto departmentDto) {
        log.info("DepartmentController: createOrUpdateDepartment");
        Department department = departmentService.saveOrUpdate(departmentDto);
        return new ResponseEntity<>(department.getId(), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        log.info("DepartmentController: getDepartments");
        List<DepartmentDto> getDepartments = departmentService.findAll();
        return new ResponseEntity<>(getDepartments, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable Long id) {
        log.info("DepartmentController: getDepartment");
        DepartmentDto department = departmentService.findById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
}