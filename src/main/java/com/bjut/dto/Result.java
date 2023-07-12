package com.bjut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean status;
    private String msg;
    private Object data;

    public static Result ok(){
        return new Result(true, "success", null);
    }
    public static Result ok(Object data){
        return new Result(true, "success", data);
    }
    public static Result ok(List<?> data){
        return new Result(true, "success", data);
    }
    public static Result fail(String errorMsg){
        return new Result(false, errorMsg, null);
    }
}
