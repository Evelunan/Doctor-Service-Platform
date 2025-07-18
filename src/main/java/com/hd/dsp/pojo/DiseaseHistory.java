package com.hd.dsp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DiseaseHistory {
    private Integer id;

    private String diseaseName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate diagnosisDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate relapseDate;
//    疾病状态 0：治疗中 1：康复
    private Integer status;

    private String notes;

    private Integer userId;
}
