package com.site.datasourse.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static Date formatDate(Date timestamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(timestamp);
        return Timestamp.valueOf(format);
    }

    public static Date formatDateDaily(Date timestamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(timestamp);
        return Timestamp.valueOf(format);
    }


}
