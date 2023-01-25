package com.stanford.springjwt.service;
// DONE

import com.stanford.springjwt.dto.UsersDto;
import com.stanford.springjwt.models.UsersProfile;
import com.stanford.springjwt.repository.UsersProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
public class UsersService {
    @Autowired
    UsersProfileRepository usersRepository;


    public List<UsersDto> findAll() {
        log.info("UsersService: findAll");
        List<UsersProfile> repo = usersRepository.findAll();
        List<UsersDto> usersDtos = new ArrayList<>();
        for (UsersProfile users : repo) {
            UsersDto usersDto = new UsersDto();
            copyProperties(users, usersDto);
            usersDtos.add(usersDto);
        }
        return usersDtos;
    }


    public UsersDto findById(Long id) {
        log.info("UsersService: findById");
        UsersProfile users = usersRepository.findById(id).orElse(null);
        UsersDto usersDto = new UsersDto();
        copyProperties(users, usersDto);
        return usersDto;
    }


    public UsersProfile saveOrUpdate(UsersDto usersDto) {
        log.info("UsersService: saveOrUpdate, {}", usersDto.getUsername());
        UsersProfile users = new UsersProfile();
        users.setUpdated(new Timestamp(System.currentTimeMillis()));
        users.setPassword(usersDto.getPassword());
        users.setUsername(usersDto.getUsername());
        users.setFirst_name(usersDto.getFirst_name());
        users.setLast_name(usersDto.getLast_name());

        return usersRepository.save(users);
    }
}