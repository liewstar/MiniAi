package com.example.miniaibackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result<T> {

    private Integer code;

    private Boolean success;

    private String message;

    private T data;

    public static<T> Result<T> ok(T data, String message){
        return new Result<T>()
                .setCode(200)
                .setData(data)
                .setSuccess(true)
                .setMessage(message);
    }

    public static<T> Result<T> ok(T data){
        return new Result<T>().setCode(200)
                .setData(data).setSuccess(true);
    }

    public static<T> Result<T> ok(String message){
        return new Result<T>().setCode(200)
                .setSuccess(true).setMessage(message);
    }

    public static<T> Result<T> ok(Integer code, String message, T data){
        return new Result<T>().setCode(code).setSuccess(true).setMessage(message).setData(data);
    }

    public static<T> Result<T> ok(String message, T data){
        return new Result<T>().setCode(200).setSuccess(true).setMessage(message).setData(data);
    }

    public static<T> Result<T> err(String message){
        return new Result<T>().setCode(500)
                .setSuccess(false).setMessage(message);
    }

    public static<T> Result<T> err(Integer code, String message){
        return new Result<T>().setCode(code)
                .setSuccess(false).setMessage(message);
    }

    public static<T> Result<T> err(Integer code, String message, T data){
        return new Result<T>().setCode(code).setSuccess(false).setMessage(message).setData(data);
    }

}
