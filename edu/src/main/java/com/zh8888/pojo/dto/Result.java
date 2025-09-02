package com.zh8888.pojo.dto;

import lombok.Data;

/**
 * 统一返回结果
 */
@Data
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "Success";
        return result;

    }
    public static Result success(Object data) {
        Result result = success();
        result.data = data;
        return result;
    }
    public static Result error(String msg) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }
}
