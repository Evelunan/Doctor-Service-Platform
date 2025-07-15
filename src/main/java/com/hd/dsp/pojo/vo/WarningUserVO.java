package com.hd.dsp.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WarningUserVO {
    Integer userId;
    String username;
    Integer processed;
    Integer unProcessed;
    Integer maxLevel;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime warningTime;
}
