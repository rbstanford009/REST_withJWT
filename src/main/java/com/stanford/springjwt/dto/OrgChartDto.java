package com.stanford.springjwt.dto;

 
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stanford.springjwt.util.CustomDateAndTimeDeserialize;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Data
public class OrgChartDto {
    private Long id;
    //pickup
    private String userName;

    private int managerId;

    private List<OrgChartDto> rankBranches;

}
