package com.stanford.springjwt.service;
// DONE

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

//

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

    public JwtResponse authenticateUserService( String username, String userPassword){

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


        List<Role> all = roleRepository.findAll();
        if(all.isEmpty()) {
            setupH2Database();
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

        System.out.println("DATABASE SUCCESS");
    }

}