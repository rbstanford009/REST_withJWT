package com.stanford.springjwt.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stanford.springjwt.util.CustomDateAndTimeDeserialize;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class SortDto {

    private String sort;
    //drop
    private String pagestart;
    private String pagesize;


}
