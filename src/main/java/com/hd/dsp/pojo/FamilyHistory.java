package com.hd.dsp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FamilyHistory {
    private Integer id;
    private String relation;
    private String diseaseName;
    private String notes;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate diagnosisDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate relapseDate;
    //    疾病状态 0：治疗中 1：康复
    private Integer status;
    private Integer userId;
}
