package com.hd.dsp.pojo;

public class HealthWarningRule {
    private Integer id;
    private String name;
    private Double lower;
    private Double upper;
// CRITICAL = 0;  DANGER = 1; WARNING = 2;INFO = 3; PRIMARY = 4;
    private Integer level;
    // 0:basic; 1:diseaseHistory; 2:familyHistory;
    private Integer type;
}
