package com.stanford.springjwt.service;

// DONE

import com.stanford.springjwt.dto.EmployeeDto;
import com.stanford.springjwt.models.Employee;
import com.stanford.springjwt.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


    public List<EmployeeDto> findAll(){
        log.info("EmployeeService: findAll");
        List<Employee> repo = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee:repo){
            EmployeeDto employeeDto = new EmployeeDto();
            copyProperties(employee, employeeDtos);
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
    }


    public EmployeeDto findById(Long id){
        log.info("EmployeeService: findById");
        Employee employee = employeeRepository.findById(id).orElse(null);
        EmployeeDto employeeDto = new EmployeeDto();
        copyProperties(employee, employeeDto);
        return employeeDto;
    }


    public Employee saveOrUpdate(EmployeeDto employeeDto){
        log.info("EmployeeService: saveOrUpdate, {}", employeeDto.getUser_id());
        Employee employee = new Employee();
        employee.setUpdated(new Timestamp(System.currentTimeMillis()));
        employee.setDepartment_id(employeeDto.getDepartment_id());
        employee.setUser_id(employeeDto.getUser_id());
        employee.setParent_id(employeeDto.getParent_id());

        return employeeRepository.save(employee);
    }
}