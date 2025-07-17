package com.hd.dsp.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageVO<T> {
    private Long total;
    private Long pageSize;
    private Long pageNum;
    private List<T> list;
}
