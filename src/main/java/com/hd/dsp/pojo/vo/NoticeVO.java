package com.hd.dsp.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NoticeVO {
    private String doctorName;
    private Integer priority;
    private String method;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate planTime;
}
