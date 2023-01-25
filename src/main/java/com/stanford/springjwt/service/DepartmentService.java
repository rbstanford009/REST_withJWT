package com.stanford.springjwt.service;
// DONE

import com.stanford.springjwt.dto.DepartmentDto;
import com.stanford.springjwt.models.Department;
import com.stanford.springjwt.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;


    public List<DepartmentDto> findAll(){
        log.info("DepartmentService: findAll");
        List<Department> repo = departmentRepository.findAll();
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        for (Department department:repo){
            DepartmentDto departmentDto = new DepartmentDto();
            copyProperties(department, departmentDto);
            departmentDtos.add(departmentDto);
        }
        return departmentDtos;
    }


    public DepartmentDto findById(Long id){
        log.info("DepartmentService: findById");
        Department department = departmentRepository.findById(id).orElse(null);
        DepartmentDto departmentDto = new DepartmentDto();
        copyProperties(department,departmentDto);
        return departmentDto;
    }


    public Department saveOrUpdate(DepartmentDto departmentDto){
        log.info("DepartmentService: saveOrUpdate, {}", departmentDto.getDepartmentName());
        Department department = new Department();
        department.setDepartment_name(departmentDto.getDepartmentName());
        department.setDescription(departmentDto.getDescription());
        department.setUpdated(new Timestamp(System.currentTimeMillis()));

        return departmentRepository.save(department);
    }
}