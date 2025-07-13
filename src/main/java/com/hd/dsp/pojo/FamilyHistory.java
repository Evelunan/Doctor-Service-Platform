package com.hd.dsp.pojo;

import lombok.Data;

@Data
public class FamilyHistory {
    private Integer id;
    private String relation;
    private String diseaseName;
    private String notes;
    private Integer userId;
}
