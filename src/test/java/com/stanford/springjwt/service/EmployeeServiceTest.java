package com.stanford.springjwt.service;

import com.stanford.springjwt.dto.EmployeeDto;
import com.stanford.springjwt.dto.SortDto;
import com.stanford.springjwt.models.Employee;
import com.stanford.springjwt.models.User;
import com.stanford.springjwt.repository.EmployeeRepository;
import com.stanford.springjwt.repository.UserRepository;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;
    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void findAll() {
        List<Employee> repo = new ArrayList<>();
        Employee one = new Employee();
        repo.add(one);
        when(employeeRepository.findAll()).thenReturn(repo);
        List<EmployeeDto> all = employeeService.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void findById() {
        Long id = 1L;
        Employee one = new Employee();
        one.setId(id);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(one));
        EmployeeDto all = employeeService.findById(id);
        assertEquals(1, all.getId());
    }


    @Test
    void saveOrUpdateSave() {
        List<Employee> repo = new ArrayList<>();
        Employee one = new Employee();
        one.setUser_id(1);
        repo.add(one);
        List<Long> getThis = new ArrayList<>();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setParent_id(1);
        employeeDto.setUser_id(1);
        employeeDto.setUser_id(1);
        employeeDto.setId(1l);
        System.out.println("");
        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(one);
        Employee employeeResult = employeeService.saveUpdateDelete(employeeDto);
        assertEquals(1, employeeResult.getUser_id());
        System.out.println("");
    }

//    @Test
//    void saveOrUpdateDelete999() {
//        List<Employee> repo = new ArrayList<>();
//        Employee one = new Employee();
//        one.setUser_id(1);
//        repo.add(one);
//        List<Long> getThis = new ArrayList<>();
//        getThis.add(1l);
//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setParent_id(999);
//        employeeDto.setUser_id(1);
//        employeeDto.setUser_id(1);
//        employeeDto.setId(1l);
//        System.out.println("");
//        when(employeeRepository.findAllById(Mockito.any(List.class))).thenReturn(repo);
//        when(employeeRepository.findAll()).thenReturn(repo);
//        when(employeeRepository.delete(Mockito.any(Employee.class)));
//        when(employeeRepository.delete(one)).;
//        Employee employeeResult = employeeService.saveUpdateDelete(employeeDto);
//        assertEquals(1, employeeResult.getUser_id());
//        System.out.println("");
//    }

    @Test
    void testFindAll() {
        List<Employee> repo = new ArrayList<>();
        Employee one = new Employee();
        repo.add(one);
        when(employeeRepository.findAll()).thenReturn(repo);
        List<EmployeeDto> all = employeeService.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void findAllChildren() {
    }

    @Test
    void getLevelFromTop() {
    }

    @Test
    void getAllEmployeesThatReportToParent() {
    }

    @Test
    void getEmployeeListByParentId() {
    }

    @Test
    void getUserData() {
    }

    @Test
    void testFindById() {
    }

    @Test
    void employeeSort() {
    }

    @Test
    void saveUpdateDelete() {
    }
    @Test
    void stringToIntFail() {

        int test = employeeService.stringToInt("A");
        assertEquals(-1, test);
        System.out.println("");
    }

    @Test
    void stringToInt() {

        int test = employeeService.stringToInt("9");
        assertEquals(9, test);

    }

    @Test
    void employeeSortASCTest() {
        List<User> users =  getUsers();
        when(userRepository.findAll()).thenReturn(users);

        SortDto sortDto = new SortDto();
        sortDto.setSort("ASC");
        sortDto.setPagesize("1");
        sortDto.setPagestart("1");

        List<String> all = employeeService.employeeSort(sortDto);
        assertEquals(1, all.size());


    }

    @Test
    void employeeSortNonASCTest() {
        List<User> users =  getUsers();
        when(userRepository.findAll()).thenReturn(users);

        SortDto sortDto = new SortDto();
        sortDto.setSort("NOASC");
        sortDto.setPagesize("1");
        sortDto.setPagestart("1");

        List<String> all = employeeService.employeeSort(sortDto);
        assertEquals(1, all.size());

    }
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for(int i =0; i < 20; i++) {
            User ua = new User();
            ua.setId((long) i);
            ua.setUsername("b"+i);
            ua.setEmail("a"+i+"@b.com");
            users.add(ua);
        }
        return users;
    }
}
