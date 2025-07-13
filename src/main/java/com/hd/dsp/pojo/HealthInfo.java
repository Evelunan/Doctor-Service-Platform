package com.hd.dsp.pojo;

import lombok.Data;

@Data
public class HealthInfo {
    private Integer id;
    private Double height;
    private Double weight;
    private Integer bloodType;
    private String allergies;
    private Integer disability;
    private Integer userId;
}
