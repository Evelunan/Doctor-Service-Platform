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

    private String status;

    private String notes;

    private Integer userId;
}
