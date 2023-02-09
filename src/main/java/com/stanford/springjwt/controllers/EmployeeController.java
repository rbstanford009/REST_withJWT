package com.stanford.springjwt.controllers;


import com.stanford.springjwt.dto.EmployeeDto;
import com.stanford.springjwt.dto.OrgChartDto;
import com.stanford.springjwt.models.*;
import com.stanford.springjwt.repository.*;
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

    // DATABASE SETUP
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @PostMapping(path = "")
    public ResponseEntity<Long> createUpdateDeleteEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("EmployeeController: createOrUpdateEmployee");
        Employee employee = employeeService.saveUpdateDelete(employeeDto);
        return new ResponseEntity<>(employee.getId(), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<EmployeeDto>> getEmployeeS() {
        log.info("EmployeeController: getEmployeeS");
        List<EmployeeDto> emp_s = employeeService.findAll();

        return new ResponseEntity<>(emp_s, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
        log.info("EmployeeController: getEmployee");
        EmployeeDto employee = employeeService.findById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping(path = "/tree/{id}")
    public ResponseEntity<List<OrgChartDto>>  getEmployeeChildren(@PathVariable Long id) {
        log.info("EmployeeController: getEmployeeChildren");
        List<OrgChartDto> emp_t  = employeeService.findAllChildren(id);
        return new ResponseEntity<>(emp_t, HttpStatus.OK);
    }

    public void showAll() {
        List<Role> roleList = roleRepository.findAll();
        List<Organization> organizationList = organizationRepository.findAll();
        List<Department> departmentList = departmentRepository.findAll();
        List<Employee> employeeList = employeeRepository.findAll();
        List<User> userList = userRepository.findAll();
        List<EmployeeDto> emp_s = employeeService.findAll();
        System.out.println("DONE");
    }
}