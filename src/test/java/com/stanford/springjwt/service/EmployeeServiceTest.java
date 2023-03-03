package com.stanford.springjwt.service;

import com.stanford.springjwt.dto.EmployeeDto;
import com.stanford.springjwt.dto.OrgChartDto;
import com.stanford.springjwt.dto.SortDto;
import com.stanford.springjwt.models.*;
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

import java.util.*;

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

    List<Department> departmentList = new ArrayList<>();
    List<Organization> org  = new ArrayList<>();
    Set<Role> roles = new HashSet<>();
    List<Employee> employeeList = new ArrayList<>();
    List<User> userList  = new ArrayList<>();


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
        System.out.println("");
    }

    @Test
    void findAllChildren() {
        setupData();
        when(employeeRepository.findAll()).thenReturn(employeeList);
        when(userRepository.findAll()).thenReturn(userList);
        List<OrgChartDto> all = employeeService.findAllChildren() ;
        assertEquals(1, all.size());
    }

    @Test
    void getLevelFromTop() {
        setupData();
        int i = employeeService.getLevelFromTop(employeeList);
        assertEquals(1, i);
    }

    @Test
    void getAllEmployeesThatReportToParent() {
        setupData();
        List<Employee> all = employeeService.getEmployeeListByParentId(employeeList,1);
        assertEquals(3, all.size());
    }

    @Test
    void getEmployeeListByParentId() {
        setupData();
        List<Employee> all = employeeService.getEmployeeListByParentId(employeeList,1);
        assertEquals(3, all.size());
    }

    @Test
    void getUserData() {
        setupData();
        List<Employee> all = employeeService.getEmployeeListByParentId(employeeList,1);
        assertEquals(3, all.size());
    }

    @Test
    void testFindById() {
        setupData();
        List<Employee> all = employeeService.getEmployeeListByParentId(employeeList,1);
        assertEquals(3, all.size());
    }

    @Test
    void employeeSort() {
        setupData();
        List<Employee> all = employeeService.getEmployeeListByParentId(employeeList,1);
        assertEquals(3, all.size());
    }


    @Test
    void stringToIntFail() {

        int test = employeeService.stringToInt("A");
        assertEquals(-1, test);
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

    public  List<Employee> getEmployeeList() {
        List<Employee> repo = new ArrayList<>();
        Employee one = new Employee();
        one.setUser_id(1);
        one.setDepartment_id(1);

        repo.add(one);

        return repo;
    }
    public void setupData() {

        // ROLES
        Role three = new Role();
        three.setId(1);
        three.setName(ERole.ROLE_ADMIN);
        roles.add(three);
        Role two = new Role();
        two.setId(2);
        two.setName(ERole.ROLE_MODERATOR);
        roles.add(two);
        Role one = new Role();
        one.setId(1);
        one.setName(ERole.ROLE_USER);
        roles.add(one);


// Organization
        Organization org1 = new Organization();
        org1.setId(1l);
        org1.setOrg_name("Wayne Enterprises");
        org1.setDescription("CEO");
        org1.setRoot(0);
        org.add(org1);

        Organization org2 = new Organization();
        org2.setId(2l);
        org2.setOrg_name("HR");
        org2.setDescription("Human Resources");
        org2.setRoot(1);
        org.add(org2);
        Organization org3 = new Organization();
        org3.setId(3l);
        org3.setOrg_name("DEV");
        org3.setDescription("Development");
        org3.setRoot(1);
        org.add(org3);
        Organization org4 = new Organization();
        org4.setId(4l);
        org4.setOrg_name("Management");
        org4.setDescription("Management");
        org4.setRoot(1);
        org.add(org4);

        // Department

        Department dept1 = new Department();
        dept1.setId(1l);
        dept1.setDepartment_name("Wayne Enterprises");
        dept1.setDescription("CEO");
        departmentList.add(dept1);

        Department dept2 = new Department();
        dept2.setId(2l);
        dept2.setDepartment_name("HR");
        dept2.setDescription("Human Resources");
        departmentList.add(dept2);
        Department dept3 = new Department();
        dept3.setId(3l);
        dept3.setDepartment_name("DEV");
        dept3.setDescription("Development");
        departmentList.add(dept3);
        Department dept4 = new Department();
        dept4.setId(4l);
        dept4.setDepartment_name("Management");
        dept4.setDescription("Management");
        departmentList.add(dept4);

        setupUserEmployee();
    }

    public void setupUserEmployee() {

        Set<Role> roles = new HashSet<>();
        Role three = new Role();
        three.setId(1);
        three.setName(ERole.ROLE_ADMIN);

        // Employee
        Employee Employee0 = new Employee();  //Bruce Wayne
        Employee0.setId(1l);
        Employee0.setUser_id(1);
        Employee0.setDepartment_id(1);
        Employee0.setParent_id(-1);
        employeeList.add(Employee0);

        // USER
        User user0 = new User();
        user0.setId(1l);
        user0.setEmail("bruce@b.com");
        user0.setPassword("pass");
        user0.setUsername("Bruce");
        user0.setRoles(roles);
        userList.add(user0);

        User user1 = new User();
        user1.setId(101l);
        user1.setEmail("user1@b.com");
        user1.setPassword("pass");
        user1.setUsername("user1 A");
        user1.setRoles(roles);
        userList.add(user1);
        // Employee Manager of HR
        Employee Employee1 = new Employee();
        Employee1.setId(101l);
        Employee1.setUser_id(101);
        Employee1.setDepartment_id(2);
        Employee1.setParent_id(1);
        employeeList.add(Employee1);

        User user2 = new User();
        user2.setId(102l);
        user2.setEmail("user2@b.com");
        user2.setPassword("pass");
        user2.setUsername("user2 B");
        user2.setRoles(roles);
        userList.add(user2);

        // Manager of DEV
        Employee Employee2 = new Employee();
        Employee2.setId(102l);
        Employee2.setUser_id(102);
        Employee2.setDepartment_id(3);
        Employee2.setParent_id(1);
        employeeList.add(Employee2);


        User user3 = new User();
        user3.setId(103l);
        user3.setEmail("user3@b.com");
        user3.setPassword("pass");
        user3.setUsername("user3 C");
        user3.setRoles(roles);
        userList.add(user3);

        // Manager of Management
        Employee Employee3 = new Employee();
        Employee3.setId(103l);
        Employee3.setUser_id(103);
        Employee3.setDepartment_id(4);
        Employee3.setParent_id(1);
        employeeList.add(Employee3);

        // DEVELOPERS  3 is development
        User user4 = new User();
        user4.setId(104l);
        user4.setEmail("user4@b.com");
        user4.setPassword("pass");
        user4.setUsername("user4 D");
        user4.setRoles(roles);
        userList.add(user4);
        Employee Employee4 = new Employee();
        Employee4.setId(104l);
        Employee4.setUser_id(104);
        Employee4.setDepartment_id(3);
        Employee4.setParent_id(102);
        employeeList.add(Employee4);


        User user5 = new User();
        user5.setId(105l);
        user5.setEmail("user5@b.com");
        user5.setPassword("pass");
        user5.setUsername("user5 F");
        user5.setRoles(roles);
        userList.add(user5);
        Employee Employee5 = new Employee();
        Employee5.setId(105l);
        Employee5.setUser_id(105);
        Employee5.setDepartment_id(3);
        Employee5.setParent_id(102);
        employeeList.add(Employee5);


        User user6 = new User();
        user6.setId(106l);
        user6.setEmail("user6@b.com");
        user6.setPassword("pass");
        user6.setUsername("user6 G");
        user6.setRoles(roles);
        userList.add(user6);
        Employee Employee6 = new Employee();
        Employee6.setId(106l);
        Employee6.setUser_id(106);
        Employee6.setDepartment_id(3);
        Employee6.setParent_id(102);
        employeeList.add(Employee6);

        User user7 = new User();
        user7.setId(107l);
        user7.setEmail("user7@b.com");
        user7.setPassword("pass");
        user7.setUsername("user7 H");
        user7.setRoles(roles);
        userList.add(user7);
        Employee Employee7 = new Employee();
        Employee7.setId(107l);
        Employee7.setUser_id(107);
        Employee7.setDepartment_id(3);
        Employee7.setParent_id(102);
        employeeList.add(Employee7);

        User user8 = new User();
        user8.setId(108l);
        user8.setEmail("user8@b.com");
        user8.setPassword("pass");
        user8.setUsername("user8 I");
        user8.setRoles(roles);
        userList.add(user8);
        Employee Employee8 = new Employee();
        Employee8.setId(108l);
        Employee8.setUser_id(108);
        Employee8.setDepartment_id(3);
        Employee8.setParent_id(102);
        employeeList.add(Employee8);

    }


    //

    //
}
