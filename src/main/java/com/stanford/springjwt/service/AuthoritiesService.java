package com.stanford.springjwt.service;
// DONE

import com.stanford.springjwt.dto.AuthoritiesDto;
import com.stanford.springjwt.models.Authorities;
import com.stanford.springjwt.repository.AuthoritiesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
@Transactional
public class AuthoritiesService {
    @Autowired
    AuthoritiesRepository authoritiesRepository;


    public List<AuthoritiesDto> findAll(){
        log.info("AuthoritiesService: findAll");
        List<Authorities> repo = authoritiesRepository.findAll();
        List<AuthoritiesDto> authoritiesDtos = new ArrayList<>();
        for (Authorities authorities:repo){
            AuthoritiesDto authoritiesDto = new AuthoritiesDto();
            copyProperties(authorities, authoritiesDto);
            authoritiesDtos.add(authoritiesDto);
        }
        return authoritiesDtos;
    }


    public AuthoritiesDto findById(Long id){
        log.info("AuthoritiesService: findById");
        Authorities authorities = authoritiesRepository.findById(id).orElse(null);
        AuthoritiesDto authoritiesDto = new AuthoritiesDto();
        copyProperties(authorities,authoritiesDto);
        return authoritiesDto;
    }


    public Authorities saveOrUpdate(AuthoritiesDto authoritiesDto){
        log.info("AuthoritiesService: saveOrUpdate, {}", authoritiesDto.getAuthority());
        Authorities authorities = new Authorities();
        authorities.setAuthority(authoritiesDto.getAuthority());
        authorities.setUser_id(authoritiesDto.getUser_id());

        return authoritiesRepository.save(authorities);
    }
}