package com.stanford.springjwt.service;


import com.stanford.springjwt.dto.EmployeeDto;
import com.stanford.springjwt.dto.OrgChartDto;
import com.stanford.springjwt.dto.SortDto;
import com.stanford.springjwt.models.Employee;
import com.stanford.springjwt.models.User;
import com.stanford.springjwt.repository.EmployeeRepository;
import com.stanford.springjwt.repository.UserRepository;
import com.stanford.springjwt.repository.UsersProfileRepository;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    UsersProfileRepository usersRepository;

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

    public List<OrgChartDto> findAllChildren() {
        log.info("EmployeeService: findAllChildren");
        List<Employee> employeeRepositoryAll = employeeRepository.findAll();
        List<User> userRepositoryAll = userRepository.findAll();
        int ceo = getLevelFromTop(employeeRepositoryAll);
        List<OrgChartDto> orgChartDto = new ArrayList<>();
        for (Employee employee : employeeRepositoryAll) {
            if (employee.getId() == ceo) {
                OrgChartDto orgChartDto1 = new OrgChartDto();
                User userData = getUserData(userRepositoryAll, ceo);
                orgChartDto1.setId(employee.getId());
                orgChartDto1.setUserName(userData.getUsername());
                orgChartDto1.setRankBranches(getAllEmployeesThatReportToParent(employeeRepositoryAll, userRepositoryAll, ceo));
                orgChartDto.add(orgChartDto1);
            }
        }
        return orgChartDto;
    }


    public int getLevelFromTop(List<Employee> orgData) {

        int top = -1;
        for (Employee employee : orgData) {
            if (employee.getParent_id() < 1) {
                top = employee.getUser_id();
            }
        }
        return top;


    }

    public List<OrgChartDto> getAllEmployeesThatReportToParent(List<Employee> orgData, List<User> usersies, int id) {
        List<OrgChartDto> orgChartDto = new ArrayList<>();
        boolean findMore = true;
        while (findMore) {
            List<Employee> employeeList = getEmployeeListByParentId(orgData, id);
            for (Employee employee : employeeList) {
                OrgChartDto orgChartDto1 = new OrgChartDto();
                User userData = getUserData(usersies, employee.getId().intValue());
                orgChartDto1.setId(employee.getId());
                orgChartDto1.setUserName(userData.getUsername());
                orgChartDto1.setManagerId(id);
                List<Employee> employeeListCheck = getEmployeeListByParentId(orgData, employee.getUser_id());
                if (employeeListCheck != null && employeeListCheck.size() > 0) {
                    orgChartDto1.setRankBranches(getAllEmployeesThatReportToParent(orgData, usersies, employee.getUser_id()));
                } else {
                    orgChartDto1.setRankBranches(null);
                }

                orgChartDto.add(orgChartDto1);
            }
            findMore = false;
        }


        return orgChartDto;


    }

    public List<Employee> getEmployeeListByParentId(List<Employee> orgData, int id) {
        List<Employee> returnEmployee = new ArrayList<>();
        for (Employee employee : orgData) {
            if (employee.getParent_id() == id) {
                returnEmployee.add(employee);
            }
        }

        return returnEmployee;
    }


    public User getUserData(List<User> users, int id) {
        for (User user : users) {
            if (user.getId().intValue() == id) {
                return user;
            }
        }

        return null;
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

    public List<EmployeeDto>  employeeSort(SortDto sortDto) {
        log.info("EmployeeService: employeeSort");
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

//

    //

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

    public int cvt(Long value) {
        int returnInt = 0;
        value.intValue();

        return returnInt;
    }
}
