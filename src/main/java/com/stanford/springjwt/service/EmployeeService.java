package com.stanford.springjwt.service;



import com.stanford.springjwt.dto.EmployeeDto;
import com.stanford.springjwt.models.Employee;
import com.stanford.springjwt.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<EmployeeDto> findAll() {
        log.info("EmployeeService: findAll");
        List<Employee> repo = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : repo) {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setParent_id(employee.getParent_id());
            employeeDto.setDepartment_id(employee.getDepartment_id());
            employeeDto.setUpdated(new Timestamp(System.currentTimeMillis()));
            employeeDto.setUser_id(employee.getUser_id());
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
    }


    public EmployeeDto findById(Long id) {
        log.info("EmployeeService: findById");
        Employee employee = employeeRepository.findById(id).orElse(null);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setParent_id(employee.getParent_id());
        employeeDto.setDepartment_id(employee.getDepartment_id());
        employeeDto.setUpdated(new Timestamp(System.currentTimeMillis()));
        employeeDto.setUser_id(employee.getUser_id());
        return employeeDto;
    }


    public Employee saveUpdateDelete(EmployeeDto employeeDto) {
        log.info("EmployeeService: saveUpdateDelete, {}", employeeDto.getUser_id());
        if (employeeDto.getParent_id() == 999) {
            Long id = employeeDto.getId();
            List<Long> getThis = new ArrayList<>();
            getThis.add(id);
            List<Employee> byId = employeeRepository.findAllById(getThis);
            Employee employeeDelete = byId.get(0);
            employeeRepository.delete(employeeDelete);
            List<Employee> all = employeeRepository.findAll();
            return employeeDelete;
        }
        Employee employee = new Employee();
        employee.setUpdated(new Timestamp(System.currentTimeMillis()));
        employee.setDepartment_id(employeeDto.getDepartment_id());
        employee.setUser_id(employeeDto.getUser_id());
        employee.setParent_id(employeeDto.getParent_id());
        return employeeRepository.save(employee);
    }
}