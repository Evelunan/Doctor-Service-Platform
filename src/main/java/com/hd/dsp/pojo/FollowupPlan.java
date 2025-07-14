package com.hd.dsp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FollowupPlan {
    private Integer id;
    private Integer elderId;
    private String elderName;
    private Integer doctorId;
    private Integer priority;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate planTime ;
    private String method;
    private String notes;
    private Integer status;

}
