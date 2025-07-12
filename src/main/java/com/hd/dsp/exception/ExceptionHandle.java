package com.hd.dsp.exception;

import com.hd.dsp.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(Exception.class)
    public Result handle(Exception e)
    {
       e.printStackTrace();
       return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage():"操作失误！");
    }
}
