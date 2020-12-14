package com.site.bdata.dto.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @program: FreeMarkeDemo
 * @description: 分页表格数据返回
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResultPage<T> implements Serializable {

    //状态码
    private int code;

    //提示消息
    private String msg;

    //总条数
    private long count;

    //表格数据
    private List<T> data;


}
