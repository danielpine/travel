package com.demo.travel.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result error(Exception e) {
        logger.error("BaseExceptionHandler : " + e.getMessage(), e);
        return new Result(false, StatusCode.ERROR, "执行出错 " + e.getMessage());
    }
}
