package com.stanford.springjwt.dto;

 
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stanford.springjwt.util.CustomDateAndTimeDeserialize;
import lombok.Data;

import java.sql.Timestamp;


@Data
public class OrganizationDto {
    private Long id;
    //pickup
    private String description;
    private String org_name;
    @JsonDeserialize(using= CustomDateAndTimeDeserialize.class)
    private Timestamp updated;
    private int root;

}
