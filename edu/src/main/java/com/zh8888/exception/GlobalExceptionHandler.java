package com.zh8888.exception;

import com.zh8888.pojo.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理其他异常
     * @param e 异常
     * @return 错误信息
     */
    @ExceptionHandler
    public Result error(Exception e) {
        log.error("错误!", e);
        return Result.error("ERROR!");
    }


    /**
     * 处理数据库异常
     * @param e 数据库异常
     * @return 错误信息
     */
    @ExceptionHandler
    public Result error(DuplicateKeyException e) {
        log.error("数据库-数据重复异常!", e);
        String message = e.getMessage();
            int i = message.indexOf("Duplicate entry");
            String substring = message.substring(i);
            String[] split = substring.split(" ");
            String msg = split[2].replace("'", "") + " 已存在!";
            return Result.error(msg);
    }

    @ExceptionHandler
    public Result error(DataIntegrityViolationException e) {
        log.error("数据库-数值超出范围!", e);
        return Result.error("分数数值超出范围!(Max:512)");
    }

    /**
     * 处理自定义异常
     * @param e 自定义异常
     * @return 错误信息
     */
    @ExceptionHandler
    public Result error(BaseException e) {
        log.error("错误!", e);
        return Result.error(e.getMessage());
    }
}
