package com.hd.dsp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.regex.Pattern;

@Data
public class FollowupHistory {
    private Integer id;
    private Integer planId;
    private String lifeStyle;
    private Integer healthStatus;
    private String medication;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String followupTime;
}
