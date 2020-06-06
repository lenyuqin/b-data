package com.site.bdata.util;

import java.sql.Timestamp;


public class DateUtils {

    /**
     * 获得本地当前时间
     * @param
     * @return java.sql.Timestamp
     * @date 2019/8/28 13:03
     */
    public static Timestamp getLocalCurrentDate(){
        return new Timestamp(System.currentTimeMillis());
    }


    public static void main(String[] args) {
        System.out.println(DateUtils.getLocalCurrentDate());
    }
}
