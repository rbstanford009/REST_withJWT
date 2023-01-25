package com.stanford.springjwt.dto;

 
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stanford.springjwt.util.CustomDateAndTimeDeserialize;
import lombok.Data;

import java.sql.Timestamp;


@Data
public class DepartmentDto {
    private Long id;

    private String departmentName;
    private String description;
    @JsonDeserialize(using= CustomDateAndTimeDeserialize.class)
    private Timestamp pickupDateTime;

}
