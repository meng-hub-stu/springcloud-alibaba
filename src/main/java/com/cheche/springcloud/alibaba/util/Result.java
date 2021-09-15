package com.cheche.springcloud.alibaba.util;

import lombok.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Mengdl
 * @date 2021/09/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -3227876720404375612L;
    private static final Integer SUCCESS_CODE = 200;
    private static final String SUCCESS_MESSAGE = "OK";

    private Integer code;
    private String message;
    private String timestamp;
    private T data;
    public static class ResultBuilder<E>{
        private Integer code;
        private String message;
        private String timestamp;
        private E data;

        public Result.ResultBuilder<E> code(Integer code){
            this.code = code;
            return this;
        }

        public Result.ResultBuilder<E> message(String message){
            this.message = message;
            return this;
        }

        public Result.ResultBuilder<E> data(E data){
            this.data = data;
            return this;
        }

        public Result<E> build(){
            return new Result<>(this.code, this.message, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS")),this.data);
        }
    }

    public static <T> ResultBuilder<T> builder(){
        return new ResultBuilder<>();
    }

    public static Result<Object> success(){
        return Result.builder().code(SUCCESS_CODE).message(SUCCESS_MESSAGE).build();
    }

    public static <T> Result<Object> success(T data){
        return Result.builder().code(SUCCESS_CODE).message(SUCCESS_MESSAGE).data(data).build();
    }

    public static Result<Object> error(Integer code, String message){
        return Result.builder().code(code).message(message).build();
    }
}
