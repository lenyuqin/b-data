package com.site.bdata.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回结果集
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int resultCode;
    private String message;
    private T data;


    public Result(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

}
