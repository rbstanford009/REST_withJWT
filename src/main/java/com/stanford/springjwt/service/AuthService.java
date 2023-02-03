package com.stanford.springjwt.service;


import com.stanford.springjwt.models.*;
import com.stanford.springjwt.payload.request.SignupRequest;
import com.stanford.springjwt.payload.response.JwtResponse;
import com.stanford.springjwt.payload.response.MessageResponse;
import com.stanford.springjwt.repository.*;
import com.stanford.springjwt.security.jwt.JwtUtils;
import com.stanford.springjwt.security.services.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    // DATABASE SETUP
    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public JwtResponse authenticateUserService(String username, String userPassword) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, userPassword));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        JwtResponse jwtResponse = new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
        return jwtResponse;

    }


    public ResponseEntity<?> registerUserService(SignupRequest signUpRequest) {

        boolean setDatabase = false;
        List<Role> all = roleRepository.findAll();
        if (all.isEmpty()) {
            setupH2Database();
            setDatabase = true;
        }
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        if (setDatabase) {
            setupUserH2Database();
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public void setupH2Database() {

        // ROLES
        Role three = new Role();
        three.setId(1);
        three.setName(ERole.ROLE_ADMIN);
        roleRepository.save(three);
        Role two = new Role();
        two.setId(2);
        two.setName(ERole.ROLE_MODERATOR);
        roleRepository.save(two);
        Role one = new Role();
        one.setId(1);
        one.setName(ERole.ROLE_USER);
        roleRepository.save(one);
// Organization
        Organization org1 = new Organization();
        org1.setId(1l);
        org1.setOrg_name("Wayne Enterprises");
        org1.setDescription("CEO");
        org1.setRoot(0);
        organizationRepository.save(org1);

        Organization org2 = new Organization();
        org2.setId(2l);
        org2.setOrg_name("HR");
        org2.setDescription("Human Resources");
        org2.setRoot(1);
        organizationRepository.save(org2);
        Organization org3 = new Organization();
        org3.setId(3l);
        org3.setOrg_name("DEV");
        org3.setDescription("Development");
        org3.setRoot(1);
        organizationRepository.save(org3);
        Organization org4 = new Organization();
        org4.setId(4l);
        org4.setOrg_name("Management");
        org4.setDescription("Management");
        org4.setRoot(1);
        organizationRepository.save(org4);

        // Department

        Department dept1 = new Department();
        dept1.setId(1l);
        dept1.setDepartment_name("Wayne Enterprises");
        dept1.setDescription("CEO");
        departmentRepository.save(dept1);

        Department dept2 = new Department();
        dept2.setId(2l);
        dept2.setDepartment_name("HR");
        dept2.setDescription("Human Resources");
        departmentRepository.save(dept2);
        Department dept3 = new Department();
        dept3.setId(3l);
        dept3.setDepartment_name("DEV");
        dept3.setDescription("Development");
        departmentRepository.save(dept3);
        Department dept4 = new Department();
        dept4.setId(4l);
        dept4.setDepartment_name("Management");
        dept4.setDescription("Management");
        departmentRepository.save(dept4);


        // Employee
        Employee Employee1 = new Employee();  //Bruce Wayne
        Employee1.setId(1l);
        Employee1.setUser_id(1);
        Employee1.setDepartment_id(1);
        Employee1.setParent_id(1);
        employeeRepository.save(Employee1);


        // Validate

        List<Role> roleList = roleRepository.findAll();
        List<Organization> organizationList = organizationRepository.findAll();
        List<Department> departmentList = departmentRepository.findAll();
        List<Employee> employeeList = employeeRepository.findAll();
        List<User> userList = userRepository.findAll();

        System.out.println("DATABASE SUCCESS");
    }

    public void setupUserH2Database() {
        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        // USER
        User user1 = new User();
        user1.setId(101l);
        user1.setEmail("user1@b.com");
        user1.setPassword("pass");
        user1.setUsername("user1");
        user1.setRoles(roles);
        userRepository.save(user1);

        // Employee Manager of HR
        Employee Employee1 = new Employee();
        Employee1.setId(101l);
        Employee1.setUser_id(101);
        Employee1.setDepartment_id(2);
        Employee1.setParent_id(1);
        employeeRepository.save(Employee1);

        User user2 = new User();
        user2.setId(102l);
        user2.setEmail("user2@b.com");
        user2.setPassword("pass");
        user2.setUsername("user2");
        user2.setRoles(roles);
        userRepository.save(user2);

        // Manager of DEV
        Employee Employee2 = new Employee();
        Employee2.setId(102l);
        Employee2.setUser_id(102);
        Employee2.setDepartment_id(3);
        Employee2.setParent_id(1);
        employeeRepository.save(Employee2);


        User user3 = new User();
        user3.setId(103l);
        user3.setEmail("user3@b.com");
        user3.setPassword("pass");
        user3.setUsername("user3");
        user3.setRoles(roles);
        userRepository.save(user3);

        // Manager of Management
        Employee Employee3 = new Employee();
        Employee3.setId(103l);
        Employee3.setUser_id(103);
        Employee3.setDepartment_id(4);
        Employee3.setParent_id(1);
        employeeRepository.save(Employee3);

        // DEVELOPERS  3 is development
        User user4 = new User();
        user4.setId(104l);
        user4.setEmail("user4@b.com");
        user4.setPassword("pass");
        user4.setUsername("user4");
        user4.setRoles(roles);
        userRepository.save(user4);
        Employee Employee4 = new Employee();
        Employee4.setId(104l);
        Employee4.setUser_id(104);
        Employee4.setDepartment_id(3);
        Employee4.setParent_id(102);
        employeeRepository.save(Employee4);


        User user5 = new User();
        user5.setId(105l);
        user5.setEmail("user5@b.com");
        user5.setPassword("pass");
        user5.setUsername("user5");
        user5.setRoles(roles);
        userRepository.save(user5);
        Employee Employee5 = new Employee();
        Employee5.setId(105l);
        Employee5.setUser_id(105);
        Employee5.setDepartment_id(3);
        Employee5.setParent_id(102);
        employeeRepository.save(Employee5);


        User user6 = new User();
        user6.setId(106l);
        user6.setEmail("user6@b.com");
        user6.setPassword("pass");
        user6.setUsername("user6");
        user6.setRoles(roles);
        userRepository.save(user6);
        Employee Employee6 = new Employee();
        Employee6.setId(106l);
        Employee6.setUser_id(106);
        Employee6.setDepartment_id(3);
        Employee6.setParent_id(102);
        employeeRepository.save(Employee6);

        User user7 = new User();
        user7.setId(107l);
        user7.setEmail("user7@b.com");
        user7.setPassword("pass");
        user7.setUsername("user7");
        user7.setRoles(roles);
        userRepository.save(user7);
        Employee Employee7 = new Employee();
        Employee7.setId(107l);
        Employee7.setUser_id(107);
        Employee7.setDepartment_id(3);
        Employee7.setParent_id(102);
        employeeRepository.save(Employee7);

        User user8 = new User();
        user8.setId(108l);
        user8.setEmail("user8@b.com");
        user8.setPassword("pass");
        user8.setUsername("user8");
        user8.setRoles(roles);
        userRepository.save(user8);
        Employee Employee8 = new Employee();
        Employee8.setId(108l);
        Employee8.setUser_id(108);
        Employee8.setDepartment_id(3);
        Employee8.setParent_id(102);
        employeeRepository.save(Employee8);


        List<User> userList = userRepository.findAll();
        List<Employee> employeeList = employeeRepository.findAll();
        System.out.println("DATABASE USERS ADDED SUCCESS");
    }

}