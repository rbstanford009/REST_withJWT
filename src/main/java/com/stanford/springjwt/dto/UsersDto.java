package com.stanford.springjwt.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stanford.springjwt.util.CustomDateAndTimeDeserialize;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UsersDto {
    private Long id;
    private Boolean enabled;
    private String first_name ;

    private String last_name;
    //drop
    private String password;
    private String username;


    @JsonDeserialize(using= CustomDateAndTimeDeserialize.class)
    private Timestamp updated;

}
