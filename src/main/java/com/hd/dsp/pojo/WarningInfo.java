package com.hd.dsp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WarningInfo {
    private Integer id;
    @TableField("name")
    private String name;

    @TableField("`level`")
    private Integer level;

    @TableField("`desc`")
    private String desc;

    @TableField("warning_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime warningTime;

    @TableField("status")
    private Boolean status;

    @TableField("suggestion")
    private String suggestion;

    @TableField("doctor_id")
    private Integer doctorId;

    @TableField("patient_id")
    private Integer patientId;

}
