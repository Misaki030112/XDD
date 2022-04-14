package com.hznu.xdd.domain;

import com.hznu.xdd.base.StatusCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Misaki
 */
@Data
public class Result implements Serializable {

    private static final Integer SUCCESS = 20000;

    private static final Integer ERROR = 1;

    private Integer code;

    private String message;

    private Object data;

    private Result(){}

    public Result(Integer code, Object data,String msg){
        this.code = code;
        this.data = data;
        this.message=msg;
    }

    public Result(Integer code,String msg){
        this.code=code;
        this.message=msg;
    }

    public Result(StatusCode statusCode,Object data){
        this.code=statusCode.getCode();
        this.message=statusCode.getMessage();
        this.data=data;
    }
    public Result(StatusCode statusCode){
        this.code=statusCode.getCode();
        this.message=statusCode.getMessage();
    }


    public static Result ok(Object data,String msg){
        return new Result(StatusCode.SUCCESS.getCode(), data,msg);
    }



}
