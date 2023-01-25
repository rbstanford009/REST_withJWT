package com.stanford.springjwt.controllers;


import com.stanford.springjwt.dto.EmployeeDto;
import com.stanford.springjwt.models.Employee;
import com.stanford.springjwt.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping(path = "")
    public ResponseEntity<Long> createOrUpdateEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("EmployeeController: createOrUpdateEmployee");
        Employee employee = employeeService.saveOrUpdate(employeeDto);
        return new ResponseEntity<>(employee.getId(), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<EmployeeDto>> getEmployeeries() {
        log.info("EmployeeController: getEmployeeries");
        List<EmployeeDto> employeeies = employeeService.findAll();
        return new ResponseEntity<>(employeeies, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
        log.info("EmployeeController: getEmployee");
        EmployeeDto employee = employeeService.findById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}