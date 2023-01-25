package com.stanford.springjwt.dto;

 
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stanford.springjwt.util.CustomDateAndTimeDeserialize;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class EmployeeDto {
    private Long id;
    @JsonDeserialize(using= CustomDateAndTimeDeserialize.class)
    private Timestamp updated;
    private int department_id;
    private int parent_id;
    private int user_id;

}
